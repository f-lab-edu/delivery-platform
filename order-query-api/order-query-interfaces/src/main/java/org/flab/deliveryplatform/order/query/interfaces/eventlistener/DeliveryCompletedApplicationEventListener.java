package org.flab.deliveryplatform.order.query.interfaces.eventlistener;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.order.query.application.port.SyncMyOrderUseCase;
import org.flab.deliveryplatform.order.query.interfaces.eventlistener.event.DeliveryCompletedApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DeliveryCompletedApplicationEventListener {

    private final SyncMyOrderUseCase syncMyOrderUseCase;

    @EventListener
    public void handle(DeliveryCompletedApplicationEvent event) {
        syncMyOrderUseCase.syncMyOrderDeliveryStatus(event.getOrderId(), event.getStatus());
    }
}
