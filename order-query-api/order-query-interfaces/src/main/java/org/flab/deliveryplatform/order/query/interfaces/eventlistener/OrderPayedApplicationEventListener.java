package org.flab.deliveryplatform.order.query.interfaces.eventlistener;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.order.query.application.port.ChangeMyOrderUseCase;
import org.flab.deliveryplatform.order.query.interfaces.eventlistener.event.MyOrderPayedApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderPayedApplicationEventListener {

    private final ChangeMyOrderUseCase changeMyOrderUseCase;

    @EventListener
    public void handle(MyOrderPayedApplicationEvent event) {
        changeMyOrderUseCase.changeMyOrderStatus(event.getOrderId(), event.getStatus());
    }
}
