package org.flab.deliveryplatform.delivery.interfaces.web;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.delivery.application.port.DispatchDeliveryUseCase;
import org.flab.deliveryplatform.delivery.application.port.dto.DispatchDeliveryCommand;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RequiredArgsConstructor
@OwnerDeliveryRestController
public class DispatchDeliveryController {

    private final DispatchDeliveryUseCase dispatchDeliveryUseCase;

    @PutMapping("/{deliveryId}/dispatch")
    public DeliveryPlatformResponse<Void> dispatch(
        @PathVariable Long deliveryId, DispatchDeliveryCommand command) {
        dispatchDeliveryUseCase.dispatchDelivery(deliveryId, command);
        return DeliveryPlatformResponse.ok(null);
    }
}
