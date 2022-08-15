package org.flab.deliveryplatform.order.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OrderLineItem {

    @Column(name = "order_line_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Long menuId;

    private String name;

    private int count;

    private int totalPrice;

    @JoinColumn(name = "order_line_item_id")
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderOptionGroup> orderOptionGroups = new ArrayList<>();

    @Builder
    private OrderLineItem(Long id, Long menuId, String name, int count, int totalPrice,
        List<OrderOptionGroup> orderOptionGroups) {
        this.id = id;
        this.menuId = menuId;
        this.name = name;
        this.count = count;
        this.totalPrice = totalPrice;
        this.orderOptionGroups = orderOptionGroups;
    }

    public int calculateTotalPrice() {
        this.totalPrice = orderOptionGroups.stream()
            .mapToInt(OrderOptionGroup::calculatePrice)
            .map(item -> item * count)
            .sum();

        return this.totalPrice;
    }
}
