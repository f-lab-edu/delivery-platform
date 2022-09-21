package org.flab.deliveryplatform.order.query.infrastructure.persistence;

import java.util.List;
import org.flab.deliveryplatform.order.query.domain.MyOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMyOrderRepository extends JpaRepository<MyOrder, Long> {

    List<MyOrder> findAllById(Long id);
}
