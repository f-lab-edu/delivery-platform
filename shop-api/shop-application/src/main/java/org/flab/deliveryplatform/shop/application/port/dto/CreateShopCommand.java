package org.flab.deliveryplatform.shop.application.port.dto;

import lombok.Getter;
import org.flab.deliveryplatform.shop.domain.PhoneNumber;
import org.flab.deliveryplatform.shop.domain.Shop;
import org.flab.deliveryplatform.shop.domain.ShopStatus;

@Getter
public class CreateShopCommand {

    private String name;

    private AddressDTO address;

    private String phoneNumber;

    public CreateShopCommand(String name, AddressDTO address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Shop toDomain() {
        return Shop.builder()
            .name(name)
            .address(address.toDomain())
            .phoneNumber(new PhoneNumber(phoneNumber))
            .status(ShopStatus.READY)
            .build();
    }
}
