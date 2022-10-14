package org.flab.deliveryplatform.order.query.interfaces.eventlistener;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.order.query.application.port.UpdateMyOrderUseCase;
import org.flab.deliveryplatform.order.query.interfaces.eventlistener.event.MyOrderPayedApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderPayedApplicationEventListener {

    private final UpdateMyOrderUseCase updateMyOrderUseCase;

    @EventListener
    public void handle(MyOrderPayedApplicationEvent event) {
        updateMyOrderUseCase.updateMyOrderStatus(event.getOrderId(), event.getStatus());
    }
}
