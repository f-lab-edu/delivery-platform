package org.flab.deliveryplatform.shop.application.service;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.shop.application.port.CreateOptionUseCase;
import org.flab.deliveryplatform.shop.application.port.ShopRepository;
import org.flab.deliveryplatform.shop.application.port.dto.OptionCommand;
import org.flab.deliveryplatform.shop.application.port.exception.ShopNotFoundException;
import org.flab.deliveryplatform.shop.domain.Shop;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateOptionService implements CreateOptionUseCase {

    private final ShopRepository shopRepository;

    @Override
    public void createOption(Long shopId, Long menuId, Long optionGroupId, OptionCommand command) {
        Shop shop = shopRepository.findById(shopId)
            .orElseThrow(() -> new ShopNotFoundException(shopId));

        shop.addOption(menuId, optionGroupId, command.toOption());

        shopRepository.save(shop);
    }
}
