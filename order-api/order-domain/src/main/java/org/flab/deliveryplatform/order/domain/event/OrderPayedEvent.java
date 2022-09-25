package org.flab.deliveryplatform.order.domain.event;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.deliveryplatform.common.event.Event;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class OrderPayedEvent extends Event {

    private Long orderId;

    public OrderPayedEvent(Long orderId) {
        this.orderId = orderId;
    }
}
