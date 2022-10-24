package org.flab.deliveryplatform.shop.application.service;

import java.util.TreeSet;
import org.flab.deliveryplatform.shop.domain.Address;
import org.flab.deliveryplatform.shop.domain.PhoneNumber;
import org.flab.deliveryplatform.shop.domain.Shop;
import org.flab.deliveryplatform.shop.domain.ShopStatus;

public class FakeShop {

    public static Shop createShop(Long id, Long ownerId) {
        return Shop.builder()
            .id(id)
            .ownerId(ownerId)
            .name("shop")
            .address(new Address("zipCode", "country", "state", "city", "street"))
            .phoneNumber(new PhoneNumber("01011112222"))
            .status(ShopStatus.READY)
            .minOrderPrice(10000)
            .menus(new TreeSet<>())
            .build();
    }
}
