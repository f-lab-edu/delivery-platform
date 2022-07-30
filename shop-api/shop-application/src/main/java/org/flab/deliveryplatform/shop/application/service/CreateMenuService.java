package org.flab.deliveryplatform.shop.application.service;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.shop.application.port.CreateMenuUseCase;
import org.flab.deliveryplatform.shop.application.port.ShopRepository;
import org.flab.deliveryplatform.shop.application.port.dto.CreateMenuCommand;
import org.flab.deliveryplatform.shop.application.port.exception.ShopNotFoundException;
import org.flab.deliveryplatform.shop.domain.Shop;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateMenuService implements CreateMenuUseCase {

    private final ShopRepository shopRepository;

    @Transactional
    @Override
    public void createMenu(Long shopId, CreateMenuCommand command) {
        Shop shop = shopRepository.findById(shopId)
            .orElseThrow(() -> new ShopNotFoundException(shopId));

        shop.addMenu(command.toMenu());
    }
}
