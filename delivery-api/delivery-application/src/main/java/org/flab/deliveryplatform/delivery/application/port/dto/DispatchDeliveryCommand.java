package org.flab.deliveryplatform.delivery.application.port.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DispatchDeliveryCommand {

    private Long riderId;

    private double longitude;

    private double latitude;

    public DispatchDeliveryCommand(Long riderId, double longitude, double latitude) {
        this.riderId = riderId;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
