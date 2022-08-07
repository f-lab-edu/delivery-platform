package org.flab.deliveryplatform.shop.infrastructure.persistence;

import java.util.Map;
import java.util.SortedSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.order.application.port.OrderValidator;
import org.flab.deliveryplatform.order.domain.Order;
import org.flab.deliveryplatform.order.domain.OrderLineItem;
import org.flab.deliveryplatform.order.domain.OrderOption;
import org.flab.deliveryplatform.order.domain.OrderOptionGroup;
import org.flab.deliveryplatform.shop.application.port.exception.ShopNotFoundException;
import org.flab.deliveryplatform.shop.domain.Menu;
import org.flab.deliveryplatform.shop.domain.Option;
import org.flab.deliveryplatform.shop.domain.OptionGroup;
import org.flab.deliveryplatform.shop.domain.Shop;
import org.flab.deliveryplatform.shop.domain.ShopStatus;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderValidatorImpl implements OrderValidator {

    private final JpaShopRepository jpaShopRepository;

    @Override
    public void validate(Order order) {
        Shop shop = findShop(order.getShopId());
        if (shop.getStatus() != ShopStatus.OPEN) {
            throw new IllegalStateException("가게가 영업중이 아닙니다");
        }

        if (order.getOrderLineItems().isEmpty()) {
            throw new IllegalStateException("주문 항목이 비어있습니다.");
        }

        if (!shop.isValidOrderPrice(order.calculateTotalPrice())) {
            throw new IllegalStateException(String.format("최소 주문 금액 %s 이상을 주문해주세요.", shop.getMinOrderPrice()));
        }

        Map<Long, Menu> menus = shop.getMenus().stream()
            .collect(Collectors.toMap(m -> m.getId(), Function.identity()));
        for (OrderLineItem orderLineItem : order.getOrderLineItems()) {
            validateOrderLineItem(orderLineItem, menus.get(orderLineItem.getMenuId()));
        }

    }

    private void validateOrderLineItem(OrderLineItem orderLineItem, Menu menu) {
        if (!menu.getName().equals(orderLineItem.getName())) {
            throw new IllegalStateException("메뉴가 변경됐습니다.");
        }

        for (OrderOptionGroup orderOptionGroup : orderLineItem.getOrderOptionGroups()) {
            validateOrderOptionGroup(orderOptionGroup, menu.getOptionGroups());
        }
    }

    private void validateOrderOptionGroup(OrderOptionGroup orderOptionGroup, SortedSet<OptionGroup> optionGroups) {
        OptionGroup optionGroup = optionGroups.stream()
            .filter(og -> orderOptionGroup.getName().equals(og.getName()))
            .filter(og -> !og.getOptions().isEmpty())
            .findAny()
            .orElseThrow(() -> new IllegalStateException("옵션 그륩이 변경되었습니다."));

        for (OrderOption orderOption : orderOptionGroup.getOrderOptions()) {
            validateOrderOption(orderOption, optionGroup.getOptions());
        }
    }

    private void validateOrderOption(OrderOption orderOption, SortedSet<Option> options) {
        options.stream()
            .filter(option ->
                orderOption.getName().equals(option.getName()) && orderOption.getPrice() == option.getPrice()
            )
            .findAny()
            .orElseThrow(() -> new IllegalStateException("옵션이 변경됐습니다."));
    }

    private Shop findShop(Long shopId) {
        return jpaShopRepository.findById(shopId)
            .orElseThrow(() -> new ShopNotFoundException(shopId));
    }
}
