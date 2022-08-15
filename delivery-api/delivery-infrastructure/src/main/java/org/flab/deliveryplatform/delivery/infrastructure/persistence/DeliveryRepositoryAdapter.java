package org.flab.deliveryplatform.delivery.infrastructure.persistence;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.delivery.application.port.DeliveryRepository;
import org.flab.deliveryplatform.delivery.domain.Delivery;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class DeliveryRepositoryAdapter implements DeliveryRepository {

    private final JpaDeliveryRepository jpaDeliveryRepository;

    @Override
    public Optional<Delivery> findById(Long id) {
        return jpaDeliveryRepository.findById(id);
    }

    @Override
    public Delivery save(Delivery delivery) {
        return jpaDeliveryRepository.save(delivery);
    }
}
