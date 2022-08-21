package org.flab.deliveryplatform.order.application.port;

import org.flab.deliveryplatform.order.domain.Order;

public interface OrderValidator {

    void validate(Order order);
}
