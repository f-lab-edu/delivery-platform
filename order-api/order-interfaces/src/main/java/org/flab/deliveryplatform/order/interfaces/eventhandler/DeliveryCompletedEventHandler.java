package org.flab.deliveryplatform.order.interfaces.eventhandler;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.delivery.domain.DeliveryCompletedEvent;
import org.flab.deliveryplatform.order.application.port.DeliverOrderUseCase;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@RequiredArgsConstructor
@Component
public class DeliveryCompletedEventHandler {

    private final DeliverOrderUseCase deliverOrderUseCase;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener
    public void handle(DeliveryCompletedEvent event) {
        deliverOrderUseCase.deliverOrder(event.getOrderId());
    }
}
