package org.flab.deliveryplatform.order.domain;

import lombok.Getter;

@Getter
public class OrderPayedEvent {

    private Long orderId;

    public OrderPayedEvent(Long orderId) {
        this.orderId = orderId;
    }
}
