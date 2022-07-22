package org.flab.deliveryplatform.shop.application.port.dto;

import lombok.Getter;
import org.flab.deliveryplatform.shop.domain.PhoneNumber;
import org.flab.deliveryplatform.shop.domain.Shop;
import org.flab.deliveryplatform.shop.domain.ShopStatus;

@Getter
public class CreateShopCommand {

    private String name;

    private AddressDto address;

    private String phoneNumber;

    public CreateShopCommand(String name, AddressDto address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Shop toShop() {
        return Shop.builder()
            .name(name)
            .address(address.toAddress())
            .phoneNumber(new PhoneNumber(phoneNumber))
            .status(ShopStatus.READY)
            .build();
    }
}
