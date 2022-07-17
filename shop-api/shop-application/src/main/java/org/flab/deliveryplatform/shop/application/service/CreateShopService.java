package org.flab.deliveryplatform.shop.application.service;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.shop.application.port.CreateShopUseCase;
import org.flab.deliveryplatform.shop.application.port.ShopRepository;
import org.flab.deliveryplatform.shop.application.port.dto.CreateShopCommand;
import org.flab.deliveryplatform.shop.domain.Shop;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateShopService implements CreateShopUseCase {

    private final ShopRepository shopRepository;

    @Override
    public void createShop(CreateShopCommand command) {
        Shop shop = command.toDomain();
        shopRepository.save(shop);
    }
}
