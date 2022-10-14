package org.flab.deliveryplatform.server.event.outbox;


import static org.flab.deliveryplatform.server.event.EventTypeConstant.DELIVERY_COMPLETED_EVENT;
import static org.flab.deliveryplatform.server.event.EventTypeConstant.ORDER_CREATED_EVENT;
import static org.flab.deliveryplatform.server.event.EventTypeConstant.ORDER_DELIVERED_EVENT;
import static org.flab.deliveryplatform.server.event.EventTypeConstant.ORDER_PAYED_EVENT;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.List;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.flab.deliveryplatform.common.event.Event;
import org.flab.deliveryplatform.common.event.EventPublisher;
import org.flab.deliveryplatform.delivery.interfaces.eventhandler.OrderPayedApplicationEvent;
import org.flab.deliveryplatform.server.sync.myorder.eventlistener.event.DeliveryCompletedApplicationEvent;
import org.flab.deliveryplatform.server.sync.myorder.eventlistener.event.MyOrderPayedApplicationEvent;
import org.flab.deliveryplatform.server.sync.myorder.eventlistener.event.OrderCreatedApplicationEvent;
import org.flab.deliveryplatform.server.sync.myorder.eventlistener.event.OrderDeliveredApplicationEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

@Component
public class MessageRelayScheduler {

    private final JpaOutBoxRepository jpaOutBoxRepository;

    private final EventPublisher eventPublisher;

    private final TransactionTemplate transactionTemplate;

    private final ObjectMapper objectMapper;

    public MessageRelayScheduler(JpaOutBoxRepository jpaOutBoxRepository,
        EventPublisher eventPublisher, TransactionTemplate transactionTemplate) {
        this.jpaOutBoxRepository = jpaOutBoxRepository;
        this.eventPublisher = eventPublisher;
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
            List<Event> events;
            switch (outBox.getEventType()) {
                case ORDER_PAYED_EVENT:
                    Event orderPayed = convertEvent(outBox.getPayload(), OrderPayedApplicationEvent.class);
                    Event myOrderPayed = convertEvent(outBox.getPayload(), MyOrderPayedApplicationEvent.class);
                    events = List.of(orderPayed, myOrderPayed);
                    break;
                case ORDER_DELIVERED_EVENT:
                    Event orderDelivered = convertEvent(outBox.getPayload(), OrderDeliveredApplicationEvent.class);
                    events = List.of(orderDelivered);
                    break;
                case ORDER_CREATED_EVENT:
                    Event orderCreated = convertEvent(outBox.getPayload(), OrderCreatedApplicationEvent.class);
                    events = List.of(orderCreated);
                    break;
                case DELIVERY_COMPLETED_EVENT:
                    Event deliveryCompleted =
                        convertEvent(outBox.getPayload(), DeliveryCompletedApplicationEvent.class);
                    events = List.of(deliveryCompleted);
                    break;
                default:
                    throw new IllegalStateException();
            }

            transactionTemplate.execute((TransactionCallback<Void>) status -> {
                eventPublisher.publishAll(events);
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
