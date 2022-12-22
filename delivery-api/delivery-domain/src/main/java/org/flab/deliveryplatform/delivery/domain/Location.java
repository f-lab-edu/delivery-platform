package org.flab.deliveryplatform.delivery.domain;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Location {

    private double longitude;

    private double latitude;

    @Builder
    private Location(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
