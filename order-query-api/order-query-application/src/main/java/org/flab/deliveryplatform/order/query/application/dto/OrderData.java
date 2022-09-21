package org.flab.deliveryplatform.order.query.application.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderData {

    private Long id;

    private List<OrderLineItemData> orderLineItems;

    private String status;

    private int totalPrice;

    private String shopName;

    private String deliveryStatus;

    @Builder
    private OrderData(Long id, List<OrderLineItemData> orderLineItems, String status,
        int totalPrice, String shopName, String deliveryStatus) {
        this.id = id;
        this.orderLineItems = orderLineItems;
        this.status = status;
        this.totalPrice = totalPrice;
        this.shopName = shopName;
        this.deliveryStatus = deliveryStatus;
    }

    @Getter
    @AllArgsConstructor
    public static class OrderLineItemData {

        private String name;
        private int count;
        private int totalPrice;
    }
}
