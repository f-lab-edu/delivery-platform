package org.flab.deliveryplatform.common.event;

import java.time.Instant;
import lombok.Getter;

@Getter
public abstract class Event {

    private Instant occurredOn;

    public Event() {
        this.occurredOn = Instant.now();
    }
}
