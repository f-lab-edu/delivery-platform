package org.flab.deliveryplatform.order.infrastructure.persistence;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.order.application.port.OrderRepository;
import org.flab.deliveryplatform.order.domain.Order;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryAdapter implements OrderRepository {

    private final JpaOrderRepository jpaOrderRepository;

    @Override
    public Order save(Order order) {
        return jpaOrderRepository.save(order);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return jpaOrderRepository.findById(id);
    }
}
