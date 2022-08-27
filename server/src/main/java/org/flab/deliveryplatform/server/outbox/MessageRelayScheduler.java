package org.flab.deliveryplatform.server.outbox;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.ArrayList;
import java.util.List;
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
            Object event = null;
            try {
                event = objectMapper.readValue(outBox.getPayload(), Class.forName(outBox.getEventType()));
            } catch (JsonProcessingException e) {
                throw new IllegalStateException("OutBox 변환 에러입니다.", e);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("OutBox 이벤트 타입이 존재하지 않습니다.", e);
            }
            applicationEventPublisher.publishEvent(event);
            completedOutBoxes.add(outBox.getId());
        });

        if (!completedOutBoxes.isEmpty()) {
            jpaOutBoxRepository.deleteAllByIdIn(completedOutBoxes);
        }
    }

}
