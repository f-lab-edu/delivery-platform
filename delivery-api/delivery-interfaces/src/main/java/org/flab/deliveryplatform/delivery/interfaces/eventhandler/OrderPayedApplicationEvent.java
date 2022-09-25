package org.flab.deliveryplatform.delivery.interfaces.eventhandler;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.deliveryplatform.common.event.Event;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class OrderPayedApplicationEvent extends Event {

    private Long orderId;

    public OrderPayedApplicationEvent(Long orderId) {
        this.orderId = orderId;
    }
}
