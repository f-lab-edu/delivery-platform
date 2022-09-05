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
import org.flab.deliveryplatform.common.domain.AggregateRoot;
import org.flab.deliveryplatform.order.domain.exception.InvalidOrderStatusException;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
@Entity
public class Order extends AggregateRoot {

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

    private int totalPrice;

    @Builder
    private Order(Long id, Long shopId, Long memberId, List<OrderLineItem> orderLineItems,
        OrderStatus status, int totalPrice) {
        this.id = id;
        this.shopId = shopId;
        this.memberId = memberId;
        this.orderLineItems = orderLineItems;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public void place() {
        this.status = OrderStatus.ORDERED;
    }

    public void payed() {
        if (this.status != OrderStatus.ORDERED) {
            throw new InvalidOrderStatusException("주문 상태가 올바르지 않아 결제 할 수 앖습니다.");
        }

        this.status = OrderStatus.PAYED;
        registerEvent(new OrderPayedApplicationEvent(id));
    }

    public void delivered() {
        if (this.status != OrderStatus.PAYED) {
            throw new InvalidOrderStatusException("주문 상태가 올바르지 않아 배송 완료 할 수 앖습니다.");
        }

        this.status = OrderStatus.DELIVERED;
    }

    public int calculateTotalPrice() {
        this.totalPrice = orderLineItems.stream()
            .mapToInt(OrderLineItem::calculateTotalPrice)
            .sum();

        return totalPrice;
    }

    public void cancel() {
        if (this.status == OrderStatus.DELIVERED) {
            throw new InvalidOrderStatusException("이미 배송이 완료된 주문은 취소할 수 없습니다.");
        }

        this.status = OrderStatus.CANCELED;
    }
}
