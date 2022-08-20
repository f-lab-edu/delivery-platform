package org.flab.deliveryplatform.delivery.domain;

public class DeliveryCompletedEvent {

    private Long orderId;

    public DeliveryCompletedEvent(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }
}
