package org.flab.deliveryplatform.delivery.infrastructure.persistence;

import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.event.EventPublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DeliveryEventPublisher implements EventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publish(Object domainEvent) {
        applicationEventPublisher.publishEvent(domainEvent);
    }

    @Override
    public void publishAll(Collection<Object> domainEvents) {
        domainEvents.stream()
            .forEach(this::publish);
    }
}
