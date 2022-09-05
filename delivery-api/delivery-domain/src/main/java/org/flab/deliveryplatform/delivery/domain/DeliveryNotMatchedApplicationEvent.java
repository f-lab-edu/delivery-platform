package org.flab.deliveryplatform.delivery.domain;

import lombok.Getter;
import org.flab.deliveryplatform.common.event.Event;

@Getter
public class DeliveryNotMatchedApplicationEvent extends Event {

    private Long deliveryId;

    private Long orderId;

    public DeliveryNotMatchedApplicationEvent(Long deliveryId, Long orderId) {
        this.deliveryId = deliveryId;
        this.orderId = orderId;
    }
}
