package org.flab.deliveryplatform.delivery.application.port;

import java.util.Optional;
import org.flab.deliveryplatform.delivery.domain.Delivery;

public interface DeliveryRepository {

    Optional<Delivery> findById(Long id);

    Delivery save(Delivery delivery);
}
