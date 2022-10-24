package org.flab.deliveryplatform.notification.interfaces.event;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.deliveryplatform.common.event.Event;

@Getter
@NoArgsConstructor
public class OrderPayedNotificationApplicationEvent extends Event {

    private Long orderId;

    private Long shopId;

    private Long memberId;

    private String orderStatus;

    private int orderTotalPrice;

    private List<OrderLineItem> orderLineItems;

    @Builder
    private OrderPayedNotificationApplicationEvent(Long orderId, Long shopId, Long memberId, String orderStatus,
        int orderTotalPrice, List<OrderLineItem> orderLineItems) {
        this.orderId = orderId;
        this.shopId = shopId;
        this.memberId = memberId;
        this.orderStatus = orderStatus;
        this.orderTotalPrice = orderTotalPrice;
        this.orderLineItems = orderLineItems;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class OrderLineItem {

        private String name;

        private int count;

        private int totalPrice;
    }

}
