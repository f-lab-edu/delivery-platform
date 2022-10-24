package org.flab.deliveryplatform.shop.application.service;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.shop.application.port.CloseShopUseCase;
import org.flab.deliveryplatform.shop.application.port.ShopRepository;
import org.flab.deliveryplatform.shop.application.port.exception.ShopNotFoundException;
import org.flab.deliveryplatform.shop.domain.Shop;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CloseShopService implements CloseShopUseCase {

    private final ShopRepository shopRepository;

    @Override
    public void closeShop(Long shopId, Long ownerId) {
        Shop shop = shopRepository.findById(shopId)
            .orElseThrow(() -> new ShopNotFoundException(shopId));
        shop.validateOwner(ownerId);
        shop.close();
        shopRepository.save(shop);
    }
}
