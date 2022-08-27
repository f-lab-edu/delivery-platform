package org.flab.deliveryplatform.common.event;

import java.util.Collection;

public interface EventPublisher {

    void publish(Object event);

    void publishAll(Collection<?> events);
}
