package org.flab.deliveryplatform.shop.domain;

import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode(of = {"zipCode", "country", "state", "city", "street"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Address {

    private String zipCode;

    private String country;

    private String state;

    private String city;

    private String street;

    public Address(String zipCode, String country, String state, String city, String street) {
        this.zipCode = zipCode;
        this.country = country;
        this.state = state;
        this.city = city;
        this.street = street;
    }

    @Override
    public String toString() {
        return String.format("(%s) %s %s %s %s", zipCode, country, state, city, street);
    }
}
