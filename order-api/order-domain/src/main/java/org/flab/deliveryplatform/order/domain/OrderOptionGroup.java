package org.flab.deliveryplatform.order.domain;

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
public class OrderOptionGroup {

    @Column(name = "order_option_group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    @JoinColumn(name = "order_option_group_id")
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderOption> orderOptions;

    @Builder
    private OrderOptionGroup(Long id, String name, List<OrderOption> orderOptions) {
        this.id = id;
        this.name = name;
        this.orderOptions = orderOptions;
    }

    public int calculatePrice() {
        return orderOptions.stream()
            .mapToInt(OrderOption::getPrice)
            .sum();
    }
}
