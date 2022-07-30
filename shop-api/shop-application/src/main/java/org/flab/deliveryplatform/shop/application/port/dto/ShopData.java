package org.flab.deliveryplatform.shop.application.port.dto;

import lombok.Getter;
import org.flab.deliveryplatform.shop.domain.Shop;
import org.flab.deliveryplatform.shop.domain.ShopStatus;

@Getter
public class ShopData {

    private Long shopId;

    private String name;

    private String address;

    private String phoneNumber;

    private ShopStatus status;

    private ShopData(Long shopId, String name, String address, String phoneNumber, ShopStatus status) {
        this.shopId = shopId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public static ShopData from(Shop shop) {
        return new ShopData(shop.getId(), shop.getName(), shop.getAddress().toString(),
            shop.getPhoneNumber().getPhoneNumber(), shop.getStatus());
    }
}
