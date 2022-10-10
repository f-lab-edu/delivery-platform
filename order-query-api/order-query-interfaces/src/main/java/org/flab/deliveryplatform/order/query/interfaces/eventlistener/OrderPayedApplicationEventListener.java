package org.flab.deliveryplatform.order.query.interfaces.eventlistener;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.order.query.application.port.SyncMyOrderUseCase;
import org.flab.deliveryplatform.order.query.interfaces.eventlistener.event.MyOrderPayedApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderPayedApplicationEventListener {

    private final SyncMyOrderUseCase syncMyOrderUseCase;

    @EventListener
    public void handle(MyOrderPayedApplicationEvent event) {
        syncMyOrderUseCase.syncMyOrderStatus(event.getOrderId(), event.getStatus());
    }
}
