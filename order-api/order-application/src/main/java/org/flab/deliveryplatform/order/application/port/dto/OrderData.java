package org.flab.deliveryplatform.order.application.port.dto;

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

    private ShopData shop;

    private DeliveryData delivery;

    private MemberData member;

    @Builder
    public OrderData(Long id, List<OrderLineItemData> orderLineItems, String status,
        int totalPrice, ShopData shop, DeliveryData delivery, MemberData member) {
        this.id = id;
        this.orderLineItems = orderLineItems;
        this.status = status;
        this.totalPrice = totalPrice;
        this.shop = shop;
        this.delivery = delivery;
        this.member = member;
    }

    @Getter
    @AllArgsConstructor
    public static class ShopData {

        private String name;
        private String phoneNumber;
        private String address;
    }

    @Getter
    @AllArgsConstructor
    public static class DeliveryData {

        private String status;
    }

    @Getter
    @AllArgsConstructor
    public static class MemberData {

        private String nickName;
        private String phoneNumber;
    }

    @Getter
    @AllArgsConstructor
    public static class OrderLineItemData {

        private String name;
        private int count;
        private int totalPrice;
    }
}
