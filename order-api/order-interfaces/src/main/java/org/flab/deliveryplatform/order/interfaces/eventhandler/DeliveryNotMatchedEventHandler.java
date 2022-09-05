package org.flab.deliveryplatform.order.interfaces.eventhandler;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.order.application.port.CancelOrderUseCase;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DeliveryNotMatchedEventHandler {

    private final CancelOrderUseCase cancelOrderUseCase;

    @EventListener
    public void handle(DeliveryNotMatchedEvent event) {
        cancelOrderUseCase.cancelOrder(event.getOrderId());
    }
}
