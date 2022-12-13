package org.flab.deliveryplatform.delivery.application.port;

import org.flab.deliveryplatform.delivery.application.port.dto.DispatchDeliveryCommand;

public interface DispatchDeliveryUseCase {

    void dispatchDelivery(Long deliveryId, DispatchDeliveryCommand command);
}
