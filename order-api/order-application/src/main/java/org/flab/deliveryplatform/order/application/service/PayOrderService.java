package org.flab.deliveryplatform.order.application.service;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.event.EventPublisher;
import org.flab.deliveryplatform.order.application.port.OrderRepository;
import org.flab.deliveryplatform.order.application.port.PayOrderUseCase;
import org.flab.deliveryplatform.order.application.port.exception.OrderNotFoundException;
import org.flab.deliveryplatform.order.domain.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PayOrderService implements PayOrderUseCase {

    private final OrderRepository orderRepository;

    private final EventPublisher eventPublisher;

    @Transactional
    @Override
    public void payOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new OrderNotFoundException(orderId));
        order.payed();
        order.getOccurredEvents().forEach(eventPublisher::publish);
    }
}
