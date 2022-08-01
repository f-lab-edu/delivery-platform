package org.flab.deliveryplatform.order.infrastructure.persistence;

import org.flab.deliveryplatform.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends JpaRepository<Order, Long> {

}
