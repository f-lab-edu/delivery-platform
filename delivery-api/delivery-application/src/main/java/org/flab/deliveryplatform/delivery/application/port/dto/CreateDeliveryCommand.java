package org.flab.deliveryplatform.delivery.application.port.dto;

import lombok.Getter;
import org.flab.deliveryplatform.delivery.domain.DeliveryStatus;

@Getter
public class CreateDeliveryCommand {

    private Long orderId;

    private DeliveryStatus deliveryStatus;

    public CreateDeliveryCommand(Long orderId, DeliveryStatus deliveryStatus) {
        this.orderId = orderId;
        this.deliveryStatus = deliveryStatus;
    }
}
