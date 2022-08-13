package org.flab.deliveryplatform.delivery.infrastructure.persistence;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.delivery.domain.Delivery;
import org.flab.deliveryplatform.delivery.domain.DeliveryStatus;
import org.flab.deliveryplatform.order.domain.OrderPayedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class OrderPayedEventHandler {

    private final JpaDeliveryRepository jpaDeliveryRepository;

    @Async
    @EventListener
    @Transactional
    public void handle(OrderPayedEvent event) {
        Delivery delivery = Delivery.builder()
            .orderId(event.getOrderId())
            .status(DeliveryStatus.DELIVERING)
            .build();

        jpaDeliveryRepository.save(delivery);
    }
}
