package org.flab.deliveryplatform.shop.application.port.dto;

import lombok.Getter;
import org.flab.deliveryplatform.shop.domain.PhoneNumber;
import org.flab.deliveryplatform.shop.domain.Shop;

@Getter
public class UpdateShopCommand {

    private String name;

    private AddressDto address;

    private String phoneNumber;

    private int minOrderPrice;

    public UpdateShopCommand(String name, AddressDto address, String phoneNumber, int minOrderPrice) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.minOrderPrice = minOrderPrice;
    }

    public Shop toShop() {
        return Shop.builder()
            .name(name)
            .address(address.toAddress())
            .phoneNumber(new PhoneNumber(phoneNumber))
            .minOrderPrice(minOrderPrice)
            .build();
    }
}
