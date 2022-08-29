package org.flab.deliveryplatform.delivery.domain;

import org.flab.deliveryplatform.common.event.Event;

public class DeliveryCompletedEvent extends Event {

    private Long orderId;

    public DeliveryCompletedEvent(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }
}
