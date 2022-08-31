package org.flab.deliveryplatform.server.event.outbox;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.ArrayList;
import java.util.List;
import org.flab.deliveryplatform.delivery.interfaces.eventhandler.OrderPayedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MessageRelayScheduler {

    private final JpaOutBoxRepository jpaOutBoxRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final ObjectMapper objectMapper;

    public MessageRelayScheduler(JpaOutBoxRepository jpaOutBoxRepository,
        ApplicationEventPublisher applicationEventPublisher) {
        this.jpaOutBoxRepository = jpaOutBoxRepository;
        this.applicationEventPublisher = applicationEventPublisher;
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Transactional
    @Scheduled(fixedDelay = 5000)
    public void handle() {
        List<OutBox> outBoxes = jpaOutBoxRepository.findAll();
        if (outBoxes.isEmpty()) {
            return;
        }

        List<Long> completedOutBoxes = new ArrayList<>();
        outBoxes.forEach(outBox -> {
            Object event;
            switch (outBox.getEventType()) {
                case "OrderPayedApplicationEvent":
                    try {
                        event = objectMapper.readValue(outBox.getPayload(), OrderPayedEvent.class);
                    } catch (JsonProcessingException e) {
                        throw new IllegalStateException("OutBox 변환 에러입니다.", e);
                    }
                    break;
                default:
                    throw new IllegalStateException();
            }

            applicationEventPublisher.publishEvent(event);
            completedOutBoxes.add(outBox.getId());
        });

        if (!completedOutBoxes.isEmpty()) {
            jpaOutBoxRepository.deleteAllByIdIn(completedOutBoxes);
        }
    }
}
