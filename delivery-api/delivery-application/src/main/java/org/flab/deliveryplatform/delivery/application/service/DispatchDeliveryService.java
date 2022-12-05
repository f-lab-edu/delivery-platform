package org.flab.deliveryplatform.delivery.application.service;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.event.EventPublisher;
import org.flab.deliveryplatform.delivery.application.port.DeliveryRepository;
import org.flab.deliveryplatform.delivery.application.port.DispatchDeliveryUseCase;
import org.flab.deliveryplatform.delivery.application.port.dto.DispatchDeliveryCommand;
import org.flab.deliveryplatform.delivery.application.port.exception.DeliveryNotFoundException;
import org.flab.deliveryplatform.delivery.domain.Delivery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DispatchDeliveryService implements DispatchDeliveryUseCase {

    private final DeliveryRepository deliveryRepository;

    private final EventPublisher eventPublisher;

    @Transactional
    @Override
    public void dispatchDelivery(Long deliveryId, DispatchDeliveryCommand command) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
            .orElseThrow(() -> new DeliveryNotFoundException(deliveryId));

        delivery.dispatch();

        eventPublisher.publishAll(delivery.getOccurredEvents());
    }
}
