package org.flab.deliveryplatform.shop.application.port.dto;

import java.util.List;
import java.util.stream.Collectors;
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

    private List<MenuData> menus;

    @Builder
    private ShopData(Long id, String name, String address, String phoneNumber,
        ShopStatus status, List<MenuData> menus) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.menus = menus;
    }

    public static ShopData from(Shop shop) {
        List<MenuData> menuDataList = shop.getMenus().stream()
            .map(MenuData::from)
            .collect(Collectors.toList());

        return ShopData.builder()
            .id(shop.getId())
            .name(shop.getName())
            .address(shop.getAddress().toString())
            .phoneNumber(shop.getPhoneNumber().getPhoneNumber())
            .status(shop.getStatus())
            .menus(menuDataList)
            .build();
    }
}
