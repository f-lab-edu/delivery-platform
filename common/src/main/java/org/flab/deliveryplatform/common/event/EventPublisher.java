package org.flab.deliveryplatform.common.event;

import java.util.Collection;

public interface EventPublisher {

    void publish(Collection<Object> domainEvents);
}
