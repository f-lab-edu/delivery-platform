package org.flab.deliveryplatform.order.query.application.port;

import org.flab.deliveryplatform.order.query.application.port.dto.CreateMyOrderCommand;

public interface CreateMyOrderUseCase {

    void createMyOrder(CreateMyOrderCommand command);
}
