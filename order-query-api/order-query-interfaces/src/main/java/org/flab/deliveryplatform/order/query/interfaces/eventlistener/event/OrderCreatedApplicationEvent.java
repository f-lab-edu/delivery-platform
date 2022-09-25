package org.flab.deliveryplatform.order.query.interfaces.eventlistener.event;

import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.deliveryplatform.common.event.Event;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class OrderCreatedApplicationEvent extends Event {

    private Long orderId;

    private Long shopId;

    private Long memberId;

    private String orderStatus;

    private int orderTotalPrice;

    private List<OrderLineItem> orderLineItems;

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    public static class OrderLineItem {

        private String name;

        private int count;

        private int totalPrice;
    }
}
