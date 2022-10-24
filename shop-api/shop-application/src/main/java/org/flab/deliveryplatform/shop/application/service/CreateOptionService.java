package org.flab.deliveryplatform.shop.application.service;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.shop.application.port.CreateOptionUseCase;
import org.flab.deliveryplatform.shop.application.port.ShopRepository;
import org.flab.deliveryplatform.shop.application.port.dto.OptionCommand;
import org.flab.deliveryplatform.shop.application.port.exception.ShopNotFoundException;
import org.flab.deliveryplatform.shop.domain.Shop;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateOptionService implements CreateOptionUseCase {

    private final ShopRepository shopRepository;

    @Transactional
    @Override
    public void createOption(Long shopId, Long ownerId, Long menuId, Long optionGroupId, OptionCommand command) {
        Shop shop = shopRepository.findById(shopId)
            .orElseThrow(() -> new ShopNotFoundException(shopId));
        shop.validateOwner(ownerId);
        shop.addOption(menuId, optionGroupId, command.toOption());
    }
}
