package org.flab.deliveryplatform.order.query.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Immutable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Immutable
@Entity
public class MyOrder {

    @Column(name = "my_order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Long memberId;

    private Long orderId;

    @Column(name = "order_line_items")
    @Convert(converter = OrderLineItemsConverter.class)
    private List<MyOrderLineItem> myOrderLineItems;

    @Column(name = "order_status")
    private String status;

    @Column(name = "order_total_price")
    private int totalPrice;

    private String shopName;

    private String deliveryStatus;

    @Builder
    private MyOrder(Long id, Long memberId, Long orderId, List<MyOrderLineItem> myOrderLineItems,
        String status, int totalPrice, String shopName, String deliveryStatus) {
        this.id = id;
        this.memberId = memberId;
        this.orderId = orderId;
        this.myOrderLineItems = myOrderLineItems;
        this.status = status;
        this.totalPrice = totalPrice;
        this.shopName = shopName;
        this.deliveryStatus = deliveryStatus;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class MyOrderLineItem {

        private String name;

        private int count;

        private int totalPrice;
    }

    public void changeStatus(String status) {
        this.status = status;
    }

    public void changeDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
}
