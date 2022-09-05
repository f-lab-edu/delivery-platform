package org.flab.deliveryplatform.order.interfaces.eventhandler;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.deliveryplatform.common.event.Event;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class DeliveryNotMatchedEvent extends Event {

    private Long deliveryId;

    private Long orderId;

    public DeliveryNotMatchedEvent(Long deliveryId, Long orderId) {
        this.deliveryId = deliveryId;
        this.orderId = orderId;
    }
}
