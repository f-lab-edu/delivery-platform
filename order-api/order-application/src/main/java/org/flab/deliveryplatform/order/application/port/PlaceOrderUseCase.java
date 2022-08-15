package org.flab.deliveryplatform.order.application.port;

import org.flab.deliveryplatform.order.application.port.dto.PlaceOrderCommand;

public interface PlaceOrderUseCase {

    void placeOrder(PlaceOrderCommand command);
}
