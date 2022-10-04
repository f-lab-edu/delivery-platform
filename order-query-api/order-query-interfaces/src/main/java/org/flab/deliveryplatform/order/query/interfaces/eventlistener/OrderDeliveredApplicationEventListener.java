package org.flab.deliveryplatform.order.query.interfaces.eventlistener;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.order.query.application.port.ChangeMyOrderUseCase;
import org.flab.deliveryplatform.order.query.interfaces.eventlistener.event.OrderDeliveredApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderDeliveredApplicationEventListener {

    private final ChangeMyOrderUseCase changeMyOrderUseCase;

    @EventListener
    public void handle(OrderDeliveredApplicationEvent event) {
        changeMyOrderUseCase.changeMyOrderStatus(event.getOrderId(), event.getStatus());
    }
}
