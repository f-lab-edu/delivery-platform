package org.flab.deliveryplatform.order.application.port.dto;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.flab.deliveryplatform.order.domain.Order;
import org.flab.deliveryplatform.order.domain.OrderLineItem;
import org.flab.deliveryplatform.order.domain.OrderOption;
import org.flab.deliveryplatform.order.domain.OrderOptionGroup;
import org.flab.deliveryplatform.order.domain.OrderStatus;

@AllArgsConstructor
@Getter
public class PlaceOrderCommand {

    private Long shopId;

    private Long memberId;

    private List<CartLineItem> cartLineItems;

    public Order toOrder() {
        List<OrderLineItem> orderLineItems = cartLineItems.stream()
            .map(CartLineItem::toOrderLineItem)
            .collect(Collectors.toList());

        return Order.builder()
            .shopId(shopId)
            .memberId(memberId)
            .status(OrderStatus.ORDERED)
            .orderLineItems(orderLineItems)
            .build();
    }

    @AllArgsConstructor
    @Getter
    static class CartLineItem {

        private Long menuId;

        private String name;

        private int count;

        private List<CartOptionGroup> cartOptionGroups;

        public OrderLineItem toOrderLineItem() {
            List<OrderOptionGroup> orderOptionGroups = cartOptionGroups.stream()
                .map(CartOptionGroup::toOrderOptionGroup)
                .collect(Collectors.toList());

            return OrderLineItem.builder()
                .menuId(menuId)
                .name(name)
                .count(count)
                .orderOptionGroups(orderOptionGroups)
                .build();
        }
    }

    @AllArgsConstructor
    @Getter
    static class CartOptionGroup {

        private String name;

        private List<CartOption> cartOptions;

        public OrderOptionGroup toOrderOptionGroup() {
            List<OrderOption> orderOptions = cartOptions.stream()
                .map(CartOption::toOrderOption)
                .collect(Collectors.toList());

            return OrderOptionGroup.builder()
                .name(name)
                .orderOptions(orderOptions)
                .build();
        }
    }

    @AllArgsConstructor
    @Getter
    static class CartOption {

        private String name;

        private int price;

        public OrderOption toOrderOption() {
            return OrderOption.builder()
                .name(name)
                .price(price)
                .build();
        }
    }
}
