package org.flab.deliveryplatform.delivery.interfaces.web;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.delivery.application.port.CompleteDeliveryUseCase;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RequiredArgsConstructor
@OwnerDeliveryRestController
public class CompleteDeliveryController {

    private final CompleteDeliveryUseCase completeDeliveryUseCase;

    @PutMapping("/{deliveryId}/complete")
    public DeliveryPlatformResponse<Void> completeDelivery(@PathVariable Long deliveryId) {
        completeDeliveryUseCase.completeDelivery(deliveryId);
        return DeliveryPlatformResponse.ok(null);
    }
}
