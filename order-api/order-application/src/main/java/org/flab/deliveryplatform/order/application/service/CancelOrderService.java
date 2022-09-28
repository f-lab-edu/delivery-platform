package org.flab.deliveryplatform.order.application.service;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.order.application.port.CancelOrderUseCase;
import org.flab.deliveryplatform.order.application.port.OrderRepository;
import org.flab.deliveryplatform.order.application.port.exception.OrderNotFoundException;
import org.flab.deliveryplatform.order.domain.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CancelOrderService implements CancelOrderUseCase {

    private final OrderRepository orderRepository;

    @Transactional
    @Override
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new OrderNotFoundException(orderId));

        order.cancel();
    }
}
