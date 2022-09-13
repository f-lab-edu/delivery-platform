package org.flab.deliveryplatform.delivery.infrastructure.persistence;

import java.util.Optional;
import org.flab.deliveryplatform.delivery.domain.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDeliveryRepository extends JpaRepository<Delivery, Long> {

    Optional<Delivery> findByOrderId(Long orderId);
}
