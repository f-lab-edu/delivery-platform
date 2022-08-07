package org.flab.deliveryplatform.order.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
@Entity
public class Order {

    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Long shopId;

    private Long memberId;

    @JoinColumn(name = "order_id")
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLineItem> orderLineItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Builder
    private Order(Long id, Long shopId, Long memberId, List<OrderLineItem> orderLineItems, OrderStatus status) {
        this.id = id;
        this.shopId = shopId;
        this.memberId = memberId;
        this.orderLineItems = orderLineItems;
        this.status = status;
    }

    public void place() {
        ordered();
    }

    private void ordered() {
        this.status = OrderStatus.ORDERED;
    }

    public int calculateTotalPrice() {
        return orderLineItems.stream()
            .mapToInt(OrderLineItem::calculatePrice)
            .sum();
    }
}
