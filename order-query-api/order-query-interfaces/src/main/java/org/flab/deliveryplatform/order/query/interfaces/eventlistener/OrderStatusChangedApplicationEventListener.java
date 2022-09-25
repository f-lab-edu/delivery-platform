package org.flab.deliveryplatform.order.query.interfaces.eventlistener;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.order.query.application.port.ChangeMyOrderUseCase;
import org.flab.deliveryplatform.order.query.interfaces.eventlistener.event.OrderStatusChangedApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderStatusChangedApplicationEventListener {

    private final ChangeMyOrderUseCase changeMyOrderUseCase;

    @EventListener
    public void handle(OrderStatusChangedApplicationEvent event) {
        changeMyOrderUseCase.changeMyOrderStatus(event.getOrderId(), event.getStatus());
    }
}
