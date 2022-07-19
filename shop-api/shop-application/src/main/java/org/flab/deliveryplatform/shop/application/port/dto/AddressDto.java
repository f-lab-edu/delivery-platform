package org.flab.deliveryplatform.shop.application.port.dto;

import lombok.Getter;
import org.flab.deliveryplatform.shop.domain.Address;

@Getter
public class AddressDto {

    private String zipCode;
    private String country;
    private String state;
    private String city;
    private String street;

    public AddressDto(String zipCode, String country, String state, String city, String street) {
        this.zipCode = zipCode;
        this.country = country;
        this.state = state;
        this.city = city;
        this.street = street;
    }

    public Address toDomain() {
        return new Address(zipCode, country, state, city, street);
    }
}
