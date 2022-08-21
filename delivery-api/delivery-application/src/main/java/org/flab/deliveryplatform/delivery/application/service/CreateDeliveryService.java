package org.flab.deliveryplatform.delivery.application.service;

import lombok.RequiredArgsConstructor;
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

    @Transactional
    @Override
    public void createDelivery(CreateDeliveryCommand command) {
        Delivery delivery = Delivery.builder()
            .orderId(command.getOrderId())
            .status(command.getDeliveryStatus())
            .build();

        deliveryRepository.save(delivery);
    }
}
