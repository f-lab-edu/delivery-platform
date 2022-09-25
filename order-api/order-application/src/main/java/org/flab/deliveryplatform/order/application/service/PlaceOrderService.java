package org.flab.deliveryplatform.order.application.service;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.event.EventPublisher;
import org.flab.deliveryplatform.order.application.port.OrderRepository;
import org.flab.deliveryplatform.order.application.port.OrderValidator;
import org.flab.deliveryplatform.order.application.port.PlaceOrderUseCase;
import org.flab.deliveryplatform.order.application.port.dto.PlaceOrderCommand;
import org.flab.deliveryplatform.order.domain.Order;
import org.flab.deliveryplatform.order.domain.event.OrderCreatedEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PlaceOrderService implements PlaceOrderUseCase {

    private final OrderRepository orderRepository;

    private final OrderValidator orderValidator;

    private final EventPublisher eventPublisher;

    @Transactional
    @Override
    public void placeOrder(PlaceOrderCommand command) {
        Order order = command.toOrder();

        orderValidator.validate(order);
        order.place();

        orderRepository.save(order);

        OrderCreatedEvent orderCreatedEvent = OrderCreatedEvent.builder()
            .orderId(order.getId())
            .shopId(order.getShopId())
            .memberId(order.getMemberId())
            .orderStatus(order.getStatus())
            .orderTotalPrice(order.getTotalPrice())
            .orderLineItems(
                order.getOrderLineItems().stream()
                    .map((ol) -> new OrderCreatedEvent.OrderLineItem(ol.getName(), ol.getCount(), ol.getTotalPrice()))
                    .collect(Collectors.toList())
            )
            .build();

        eventPublisher.publish(orderCreatedEvent);
    }
}
