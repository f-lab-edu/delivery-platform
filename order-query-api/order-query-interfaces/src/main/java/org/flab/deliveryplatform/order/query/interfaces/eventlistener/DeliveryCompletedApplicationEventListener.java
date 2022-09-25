package org.flab.deliveryplatform.order.query.interfaces.eventlistener;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.order.query.application.port.ChangeMyOrderUseCase;
import org.flab.deliveryplatform.order.query.interfaces.eventlistener.event.DeliveryCompletedApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DeliveryCompletedApplicationEventListener {

    private final ChangeMyOrderUseCase changeMyOrderUseCase;

    @EventListener
    public void handle(DeliveryCompletedApplicationEvent event) {
        changeMyOrderUseCase.changeMyOrderDeliveryStatus(event.getOrderId(), event.getStatus());
    }
}
