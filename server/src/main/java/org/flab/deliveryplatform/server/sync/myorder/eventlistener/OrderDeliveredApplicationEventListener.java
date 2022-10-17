package org.flab.deliveryplatform.server.sync.myorder.eventlistener;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.order.query.application.port.UpdateMyOrderUseCase;
import org.flab.deliveryplatform.server.sync.myorder.eventlistener.event.OrderDeliveredApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderDeliveredApplicationEventListener {

    private final UpdateMyOrderUseCase updateMyOrderUseCase;

    @EventListener
    public void handle(OrderDeliveredApplicationEvent event) {
        updateMyOrderUseCase.updateMyOrderStatus(event.getOrderId(), event.getStatus());
    }
}
