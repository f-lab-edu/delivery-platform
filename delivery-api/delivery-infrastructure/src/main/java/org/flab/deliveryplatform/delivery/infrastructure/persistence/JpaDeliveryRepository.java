package org.flab.deliveryplatform.delivery.infrastructure.persistence;

import org.flab.deliveryplatform.delivery.domain.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDeliveryRepository extends JpaRepository<Delivery, Long> {

}
