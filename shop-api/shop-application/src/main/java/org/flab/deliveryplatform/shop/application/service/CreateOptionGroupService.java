package org.flab.deliveryplatform.shop.application.service;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.shop.application.port.CreateOptionGroupUseCase;
import org.flab.deliveryplatform.shop.application.port.ShopRepository;
import org.flab.deliveryplatform.shop.application.port.dto.CreateOptionGroupCommand;
import org.flab.deliveryplatform.shop.application.port.exception.ShopNotFoundException;
import org.flab.deliveryplatform.shop.domain.Shop;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateOptionGroupService implements CreateOptionGroupUseCase {

    private final ShopRepository shopRepository;

    @Transactional
    @Override
    public void createOptionGroup(Long shopId, Long ownerId, Long menuId, CreateOptionGroupCommand command) {
        Shop shop = shopRepository.findById(shopId)
            .orElseThrow(() -> new ShopNotFoundException(shopId));
        shop.validateOwner(ownerId);
        shop.addOptionGroup(menuId, command.toOptionGroup());
    }
}
