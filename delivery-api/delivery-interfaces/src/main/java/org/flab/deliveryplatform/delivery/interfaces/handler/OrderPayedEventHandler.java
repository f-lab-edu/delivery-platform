package org.flab.deliveryplatform.delivery.interfaces.handler;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.delivery.application.port.CreateDeliveryUseCase;
import org.flab.deliveryplatform.delivery.application.port.dto.CreateDeliveryCommand;
import org.flab.deliveryplatform.delivery.domain.DeliveryStatus;
import org.flab.deliveryplatform.order.domain.OrderPayedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@RequiredArgsConstructor
@Component
public class OrderPayedEventHandler {

    private final CreateDeliveryUseCase createDeliveryUseCase;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener
    public void handle(OrderPayedEvent event) {
        CreateDeliveryCommand command = new CreateDeliveryCommand(event.getOrderId(), DeliveryStatus.DELIVERING);
        createDeliveryUseCase.createDelivery(command);
    }
}
