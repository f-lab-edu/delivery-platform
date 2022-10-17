package org.flab.deliveryplatform.delivery.interfaces.eventhandler;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.deliveryplatform.common.event.Event;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class OrderPayedApplicationEvent extends Event {

    private Long orderId;

    private String status;

    public OrderPayedApplicationEvent(Long orderId, String status) {
        this.orderId = orderId;
        this.status = status;
    }
}
