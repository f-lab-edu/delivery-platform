package org.flab.deliveryplatform.delivery.domain;

import lombok.Getter;
import org.flab.deliveryplatform.common.event.Event;

@Getter
public class DeliveryDispatchedEvent extends Event {

    private Long deliveryId;

    private DeliveryStatus status;

    public DeliveryDispatchedEvent(Long deliveryId, DeliveryStatus status) {
        this.deliveryId = deliveryId;
        this.status = status;
    }
}
