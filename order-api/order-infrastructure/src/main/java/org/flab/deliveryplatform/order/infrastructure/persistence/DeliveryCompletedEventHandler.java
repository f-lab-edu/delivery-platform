package org.flab.deliveryplatform.order.infrastructure.persistence;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.delivery.domain.DeliveryCompletedEvent;
import org.flab.deliveryplatform.order.application.port.exception.OrderNotFoundException;
import org.flab.deliveryplatform.order.domain.Order;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class DeliveryCompletedEventHandler {

    private final JpaOrderRepository jpaOrderRepository;

    @Async
    @EventListener
    @Transactional
    public void handle(DeliveryCompletedEvent event) {
        Order order = jpaOrderRepository.findById(event.getOrderId())
            .orElseThrow(() -> new OrderNotFoundException(event.getOrderId()));

        order.delivered();

        jpaOrderRepository.save(order);
    }
}
