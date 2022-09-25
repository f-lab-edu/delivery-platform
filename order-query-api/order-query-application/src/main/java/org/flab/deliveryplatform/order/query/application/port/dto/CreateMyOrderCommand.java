package org.flab.deliveryplatform.order.query.application.port.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.flab.deliveryplatform.order.query.domain.MyOrder;

@Getter
public class CreateMyOrderCommand {

    private Long orderId;

    private Long shopId;

    private Long memberId;

    private String orderStatus;

    private int orderTotalPrice;

    private List<OrderLineItem> orderLineItems;

    @Builder
    private CreateMyOrderCommand(Long orderId, Long shopId, Long memberId, String orderStatus,
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
}
