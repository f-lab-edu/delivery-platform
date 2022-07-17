package org.flab.deliveryplatform.shop.application.service;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.shop.application.port.ShopRepository;
import org.flab.deliveryplatform.shop.application.port.UpdateShopUseCase;
import org.flab.deliveryplatform.shop.application.port.dto.UpdateShopCommand;
import org.flab.deliveryplatform.shop.application.port.exception.ShopNotFoundException;
import org.flab.deliveryplatform.shop.domain.Shop;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateShopService implements UpdateShopUseCase {

    private final ShopRepository shopRepository;

    @Override
    public void updateShop(Long shopId, UpdateShopCommand command) throws ShopNotFoundException {
        Shop shop = shopRepository.findById(shopId)
            .orElseThrow(() -> new ShopNotFoundException(shopId));

        shop.changeWith(command.toDomain());
        shopRepository.save(shop);
    }
}
