package org.flab.deliveryplatform.delivery.application.port;

import org.flab.deliveryplatform.delivery.application.port.dto.CreateDeliveryCommand;

public interface CreateDeliveryUseCase {

    void createDelivery(CreateDeliveryCommand command);
}
