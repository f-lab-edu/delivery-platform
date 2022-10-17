package org.flab.deliveryplatform.order.domain.event;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.deliveryplatform.common.event.Event;
import org.flab.deliveryplatform.order.domain.OrderStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderStatusChangedEvent extends Event {

    private Long orderId;

    private OrderStatus status;

    public OrderStatusChangedEvent(Long orderId, OrderStatus status) {
        this.orderId = orderId;
        this.status = status;
    }
}
