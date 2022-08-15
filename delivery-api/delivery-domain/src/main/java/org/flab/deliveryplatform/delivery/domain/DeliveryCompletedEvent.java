package org.flab.deliveryplatform.delivery.domain;

public class DeliveryCompletedEvent {

    private Delivery delivery;

    public DeliveryCompletedEvent(Delivery delivery) {
        this.delivery = delivery;
    }

    public Long getOrderId() {
        return delivery.getOrderId();
    }
}
