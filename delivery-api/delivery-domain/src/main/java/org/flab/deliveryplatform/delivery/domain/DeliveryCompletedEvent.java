package org.flab.deliveryplatform.delivery.domain;

import lombok.Getter;
import org.flab.deliveryplatform.common.event.Event;

@Getter
public class DeliveryCompletedEvent extends Event {

    private Long deliveryId;

    private Long orderId;

    private DeliveryStatus status;

    public DeliveryCompletedEvent(Long deliveryId, Long orderId, DeliveryStatus status) {
        this.deliveryId = deliveryId;
        this.orderId = orderId;
        this.status = status;
    }
}
