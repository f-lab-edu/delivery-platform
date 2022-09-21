package org.flab.deliveryplatform.order.query.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Immutable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Immutable
@Entity
public class MyOrder {

    @Column(name = "member_id")
    @Id
    private Long id;

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

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class MyOrderLineItem {

        private String name;

        private int count;

        private int totalPrice;
    }
}
