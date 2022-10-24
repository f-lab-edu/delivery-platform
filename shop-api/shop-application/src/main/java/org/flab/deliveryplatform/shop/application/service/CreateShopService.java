package org.flab.deliveryplatform.shop.application.service;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.shop.application.port.CreateShopUseCase;
import org.flab.deliveryplatform.shop.application.port.ShopRepository;
import org.flab.deliveryplatform.shop.application.port.dto.CreateShopCommand;
import org.flab.deliveryplatform.shop.domain.PhoneNumber;
import org.flab.deliveryplatform.shop.domain.Shop;
import org.flab.deliveryplatform.shop.domain.ShopStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateShopService implements CreateShopUseCase {

    private final ShopRepository shopRepository;

    @Override
    public void createShop(Long ownerId, CreateShopCommand command) {
        Shop shop = Shop.builder()
            .ownerId(ownerId)
            .name(command.getName())
            .address(command.getAddress().toAddress())
            .phoneNumber(new PhoneNumber(command.getPhoneNumber()))
            .status(ShopStatus.READY)
            .minOrderPrice(command.getMinOrderPrice())
            .build();
        shopRepository.save(shop);
    }
}
