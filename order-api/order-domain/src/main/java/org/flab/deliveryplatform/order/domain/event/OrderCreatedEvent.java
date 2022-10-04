package org.flab.deliveryplatform.order.domain.event;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.deliveryplatform.common.event.Event;
import org.flab.deliveryplatform.order.domain.Order;
import org.flab.deliveryplatform.order.domain.OrderStatus;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class OrderCreatedEvent extends Event {

    private Long orderId;

    private Long shopId;

    private Long memberId;

    private OrderStatus orderStatus;

    private int orderTotalPrice;

    private List<OrderLineItem> orderLineItems;

    @Builder
    private OrderCreatedEvent(Long orderId, Long shopId, Long memberId, OrderStatus orderStatus,
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
    public static class OrderLineItem {

        private String name;

        private int count;

        private int totalPrice;
    }

    public static OrderCreatedEvent from(Order order) {
        return OrderCreatedEvent.builder()
            .orderId(order.getId())
            .shopId(order.getShopId())
            .memberId(order.getMemberId())
            .orderStatus(order.getStatus())
            .orderTotalPrice(order.getTotalPrice())
            .orderLineItems(
                order.getOrderLineItems().stream()
                    .map((ol) -> new OrderCreatedEvent.OrderLineItem(ol.getName(), ol.getCount(), ol.getTotalPrice()))
                    .collect(Collectors.toList())
            )
            .build();
    }
}
