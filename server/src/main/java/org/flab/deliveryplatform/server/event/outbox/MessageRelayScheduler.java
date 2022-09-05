package org.flab.deliveryplatform.server.event.outbox;


import static org.flab.deliveryplatform.server.event.EventTypeConstant.DELIVERY_NOT_MATCHED_APPLICATION_EVENT;
import static org.flab.deliveryplatform.server.event.EventTypeConstant.ORDER_PAYED_APPLICATION_EVENT;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.List;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.flab.deliveryplatform.common.event.Event;
import org.flab.deliveryplatform.delivery.interfaces.eventhandler.OrderPayedEvent;
import org.flab.deliveryplatform.order.interfaces.eventhandler.DeliveryNotMatchedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

@Component
public class MessageRelayScheduler {

    private final JpaOutBoxRepository jpaOutBoxRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final TransactionTemplate transactionTemplate;

    private final ObjectMapper objectMapper;

    public MessageRelayScheduler(JpaOutBoxRepository jpaOutBoxRepository,
        ApplicationEventPublisher applicationEventPublisher, TransactionTemplate transactionTemplate) {
        this.jpaOutBoxRepository = jpaOutBoxRepository;
        this.applicationEventPublisher = applicationEventPublisher;
        this.transactionTemplate = transactionTemplate;
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @SchedulerLock(name = "handleOutBox")
    @Scheduled(fixedDelay = 5000)
    public void handle() {
        List<OutBox> outBoxes = jpaOutBoxRepository.findAll();
        if (outBoxes.isEmpty()) {
            return;
        }

        outBoxes.forEach(outBox -> {
            Object event;
            switch (outBox.getEventType()) {
                case ORDER_PAYED_APPLICATION_EVENT:
                    event = convertEvent(outBox.getPayload(), OrderPayedEvent.class);
                    break;
                case DELIVERY_NOT_MATCHED_APPLICATION_EVENT:
                    event = convertEvent(outBox.getPayload(), DeliveryNotMatchedEvent.class);
                    break;
                default:
                    throw new IllegalStateException();
            }

            transactionTemplate.execute((TransactionCallback<Void>) status -> {
                applicationEventPublisher.publishEvent(event);
                jpaOutBoxRepository.deleteById(outBox.getId());
                return null;
            });

            // TODO: 여러번 실패할 경우 dead letter queue 에 저장
        });
    }

    private Event convertEvent(String payload, Class<? extends Event> clazz) {
        Event event;
        try {
            event = objectMapper.readValue(payload, clazz);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("OutBox -> Event 변환 에러입니다.", e);
        }
        return event;
    }
}
