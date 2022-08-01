package org.flab.deliveryplatform.order.application.port;

import org.flab.deliveryplatform.order.domain.Order;

public interface OrderRepository {

    Order save(Order order);
}
