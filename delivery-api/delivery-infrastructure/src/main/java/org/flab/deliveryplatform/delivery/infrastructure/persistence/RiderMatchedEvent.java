package org.flab.deliveryplatform.delivery.infrastructure.persistence;

import lombok.Builder;
import lombok.Getter;
import org.flab.deliveryplatform.common.event.Event;

@Getter
public class RiderMatchedEvent extends Event {

    private Long riderId;

    private Long deliveryId;

    private Long orderId;

    @Builder
    private RiderMatchedEvent(Long riderId, Long deliveryId, Long orderId) {
        this.riderId = riderId;
        this.deliveryId = deliveryId;
        this.orderId = orderId;
    }
}
