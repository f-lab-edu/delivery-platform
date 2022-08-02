package org.flab.deliveryplatform.shop.application.service;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.shop.application.port.DeleteOptionUseCase;
import org.flab.deliveryplatform.shop.application.port.ShopRepository;
import org.flab.deliveryplatform.shop.application.port.exception.ShopNotFoundException;
import org.flab.deliveryplatform.shop.domain.Shop;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeleteOptionService implements DeleteOptionUseCase {

    private final ShopRepository shopRepository;

    @Transactional
    @Override
    public void deleteOption(Long shopId, Long menuId, Long optionGroupId, Long optionId) {
        Shop shop = shopRepository.findById(shopId)
            .orElseThrow(() -> new ShopNotFoundException(shopId));

        shop.deleteOption(menuId, optionGroupId, optionId);
    }
}
