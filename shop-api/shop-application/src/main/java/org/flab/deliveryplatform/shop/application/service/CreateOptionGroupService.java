package org.flab.deliveryplatform.shop.application.service;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.shop.application.port.CreateOptionGroupUseCase;
import org.flab.deliveryplatform.shop.application.port.ShopRepository;
import org.flab.deliveryplatform.shop.application.port.dto.CreateOptionGroupCommand;
import org.flab.deliveryplatform.shop.application.port.exception.ShopNotFoundException;
import org.flab.deliveryplatform.shop.domain.Shop;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateOptionGroupService implements CreateOptionGroupUseCase {

    private final ShopRepository shopRepository;

    @Override
    public void createOptionGroup(Long shopId, Long menuId, CreateOptionGroupCommand command) {
        Shop shop = shopRepository.findById(shopId)
            .orElseThrow(() -> new ShopNotFoundException(shopId));

        shop.addOptionGroup(menuId, command.toOptionGroup());

        shopRepository.save(shop);
    }
}
