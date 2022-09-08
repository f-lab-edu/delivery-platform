package org.flab.deliveryplatform.server.event;

import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.event.Event;
import org.flab.deliveryplatform.common.event.EventPublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SimpleEventPublisher implements EventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publish(Event domainEvent) {
        applicationEventPublisher.publishEvent(domainEvent);
    }

    @Override
    public void publishAll(Collection<Event> domainEvents) {
        domainEvents.forEach(this::publish);
    }
}
