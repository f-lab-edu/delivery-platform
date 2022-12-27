package org.flab.deliveryplatform.shop.domain;

import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Location {

    private double longitude;

    private double latitude;
    
    public Location(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
