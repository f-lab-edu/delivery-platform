package org.flab.deliveryplatform.order.domain.event;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.deliveryplatform.common.event.Event;
import org.flab.deliveryplatform.order.domain.OrderStatus;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class OrderDeliveredEvent extends Event {

    private Long orderId;

    private OrderStatus status;

    public OrderDeliveredEvent(Long orderId, OrderStatus status) {
        this.orderId = orderId;
        this.status = status;
    }
}
