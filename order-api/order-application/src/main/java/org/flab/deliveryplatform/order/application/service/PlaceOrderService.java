package org.flab.deliveryplatform.order.application.service;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.order.application.port.OrderRepository;
import org.flab.deliveryplatform.order.application.port.PlaceOrderUseCase;
import org.flab.deliveryplatform.order.application.port.dto.PlaceOrderCommand;
import org.flab.deliveryplatform.order.domain.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PlaceOrderService implements PlaceOrderUseCase {

    private final OrderRepository orderRepository;

    @Transactional
    @Override
    public void placeOrder(PlaceOrderCommand command) {
        Order order = command.toOrder();

        // TODO: 주문 호출, 유효성 검증

        orderRepository.save(order);
    }
}
