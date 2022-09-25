package org.flab.deliveryplatform.order.query.interfaces.eventlistener;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.deliveryplatform.common.event.Event;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderStatusChangedApplicationEvent extends Event {

    private Long orderId;

    private String status;

    public OrderStatusChangedApplicationEvent(Long orderId, String status) {
        this.orderId = orderId;
        this.status = status;
    }
}
