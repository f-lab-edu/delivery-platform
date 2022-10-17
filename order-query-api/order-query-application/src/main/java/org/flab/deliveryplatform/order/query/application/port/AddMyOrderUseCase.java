package org.flab.deliveryplatform.order.query.application.port;

import org.flab.deliveryplatform.order.query.application.port.dto.AddMyOrderCommand;

public interface AddMyOrderUseCase {

    void addMyOrder(AddMyOrderCommand command);

}
