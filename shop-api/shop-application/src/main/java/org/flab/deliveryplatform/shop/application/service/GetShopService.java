package org.flab.deliveryplatform.shop.application.service;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.shop.application.port.GetShopUseCase;
import org.flab.deliveryplatform.shop.application.port.ShopRepository;
import org.flab.deliveryplatform.shop.application.port.dto.ShopData;
import org.flab.deliveryplatform.shop.application.port.exception.ShopNotFoundException;
import org.flab.deliveryplatform.shop.domain.Shop;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GetShopService implements GetShopUseCase {

    private final ShopRepository shopRepository;

    @Transactional(readOnly = true)
    @Override
    public ShopData getShop(Long shopId) {
        Shop shop = shopRepository.findById(shopId)
            .orElseThrow(() -> new ShopNotFoundException(shopId));
        return ShopData.from(shop);
    }
}
