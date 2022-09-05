package org.flab.deliveryplatform.delivery.application.service;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.event.EventPublisher;
import org.flab.deliveryplatform.delivery.application.port.CreateDeliveryUseCase;
import org.flab.deliveryplatform.delivery.application.port.DeliveryRepository;
import org.flab.deliveryplatform.delivery.application.port.dto.CreateDeliveryCommand;
import org.flab.deliveryplatform.delivery.domain.Delivery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateDeliveryService implements CreateDeliveryUseCase {

    private final DeliveryRepository deliveryRepository;

    private final EventPublisher eventPublisher;

    @Transactional
    @Override
    public void createDelivery(CreateDeliveryCommand command) {
        Delivery delivery = Delivery.builder()
            .orderId(command.getOrderId())
            .build();

        if (canDeliver()) {
            delivery.start();
        } else {
            delivery.notMatched();
        }

        deliveryRepository.save(delivery);

        eventPublisher.publishAll(delivery.getOccurredEvents());
    }

    private boolean canDeliver() {
        // TODO: 배송 기사 매칭 여부
        int random = (int) (Math.random() * 2) + 1;
        return random % 2 == 0;
    }
}
