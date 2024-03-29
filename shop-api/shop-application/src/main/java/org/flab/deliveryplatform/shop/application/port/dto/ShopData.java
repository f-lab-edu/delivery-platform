package org.flab.deliveryplatform.shop.application.port.dto;

import lombok.Builder;
import lombok.Getter;
import org.flab.deliveryplatform.shop.domain.Shop;
import org.flab.deliveryplatform.shop.domain.ShopStatus;

@Getter
public class ShopData {

    private Long id;

    private String name;

    private String address;

    private String phoneNumber;

    private ShopStatus status;

    private int minOrderPrice;

    @Builder
    private ShopData(Long id, String name, String address, String phoneNumber, ShopStatus status, int minOrderPrice) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.minOrderPrice = minOrderPrice;
    }

    public static ShopData from(Shop shop) {
        return ShopData.builder()
            .id(shop.getId())
            .name(shop.getName())
            .address(shop.getAddress().toString())
            .phoneNumber(shop.getPhoneNumber().getPhoneNumber())
            .status(shop.getStatus())
            .minOrderPrice(shop.getMinOrderPrice())
            .build();
    }
}
