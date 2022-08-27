package org.flab.deliveryplatform.delivery.application.service;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.event.EventPublisher;
import org.flab.deliveryplatform.delivery.application.port.CompleteDeliveryUseCase;
import org.flab.deliveryplatform.delivery.application.port.DeliveryRepository;
import org.flab.deliveryplatform.delivery.application.port.exception.DeliveryNotFoundException;
import org.flab.deliveryplatform.delivery.domain.Delivery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CompleteDeliveryService implements CompleteDeliveryUseCase {

    private final DeliveryRepository deliveryRepository;

    private final EventPublisher deliveryEventPublisher;

    @Transactional
    @Override
    public void completeDelivery(Long deliveryId) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
            .orElseThrow(() -> new DeliveryNotFoundException(deliveryId));

        delivery.complete();

        deliveryEventPublisher.publishAll(delivery.getOccurredEvents());
    }
}
