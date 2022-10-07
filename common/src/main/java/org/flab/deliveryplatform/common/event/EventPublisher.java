package org.flab.deliveryplatform.common.event;

import java.util.Collection;

public interface EventPublisher {

    void publish(Event domainEvent);

    void publishAll(Collection<Event> domainEvents);
}
