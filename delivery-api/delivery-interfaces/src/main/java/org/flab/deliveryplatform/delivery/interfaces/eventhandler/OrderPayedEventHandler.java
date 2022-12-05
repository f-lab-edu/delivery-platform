package org.flab.deliveryplatform.delivery.interfaces.eventhandler;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.delivery.application.port.CreateDeliveryUseCase;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderPayedEventHandler {

    private final CreateDeliveryUseCase createDeliveryUseCase;

    @EventListener
    public void handle(OrderPayedApplicationEvent event) {
        createDeliveryUseCase.createDelivery(event.getOrderId());
    }
}
