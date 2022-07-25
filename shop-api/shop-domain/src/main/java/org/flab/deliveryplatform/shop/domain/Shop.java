package org.flab.deliveryplatform.shop.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Shop {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Embedded
    private PhoneNumber phoneNumber;

    @Embedded
    private Address address;

    @Enumerated(value = EnumType.STRING)
    private ShopStatus status;

    @Builder
    private Shop(Long id, String name, PhoneNumber phoneNumber, Address address, ShopStatus status) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.status = status;
    }

    public void changeWith(Shop source) {
        this.name = source.name;
        this.phoneNumber = source.phoneNumber;
        this.address = source.address;
    }

    public void close() {
        this.status = ShopStatus.CLOSED;
    }
}
