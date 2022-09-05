package org.flab.deliveryplatform.delivery.application.port.dto;

import lombok.Getter;

@Getter
public class CreateDeliveryCommand {

    private Long orderId;


    public CreateDeliveryCommand(Long orderId) {
        this.orderId = orderId;
    }
}
