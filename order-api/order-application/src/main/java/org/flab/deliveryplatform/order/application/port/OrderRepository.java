package org.flab.deliveryplatform.order.application.port;

import java.util.Optional;
import org.flab.deliveryplatform.order.domain.Order;

public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findById(Long id);
}
