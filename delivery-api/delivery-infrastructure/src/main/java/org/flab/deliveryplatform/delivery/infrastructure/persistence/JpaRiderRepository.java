package org.flab.deliveryplatform.delivery.infrastructure.persistence;

import org.flab.deliveryplatform.delivery.domain.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRiderRepository extends JpaRepository<Rider, Long> {
}
