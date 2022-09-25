package org.flab.deliveryplatform.order.query.infrastructure.persistence;

import java.util.List;
import java.util.Optional;
import org.flab.deliveryplatform.order.query.domain.MyOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMyOrderRepository extends JpaRepository<MyOrder, Long> {

    List<MyOrder> findAllById(Long id);

    Optional<MyOrder> findByOrderId(Long orderId);
}
