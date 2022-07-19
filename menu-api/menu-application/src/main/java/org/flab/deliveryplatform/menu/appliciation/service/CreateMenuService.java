package org.flab.deliveryplatform.menu.appliciation.service;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.domain.Menu;
import org.flab.deliveryplatform.domain.ShopId;
import org.flab.deliveryplatform.menu.appliciation.port.CreateMenuUseCase;
import org.flab.deliveryplatform.menu.appliciation.port.ExistsShopPort;
import org.flab.deliveryplatform.menu.appliciation.port.MenuRepository;
import org.flab.deliveryplatform.menu.appliciation.port.dto.CreateMenuCommand;
import org.flab.deliveryplatform.menu.appliciation.port.exception.ShopNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateMenuService implements CreateMenuUseCase {

    private final MenuRepository menuRepository;

    private final ExistsShopPort existsShopPort;

    @Override
    public void createMenu(Long shopId, CreateMenuCommand command) {
        if (!existsShopPort.existsShopById(shopId)) {
            throw new ShopNotFoundException(shopId);
        }

        Menu menu = Menu.builder()
            .name(command.getName())
            .shopId(new ShopId(shopId))
            .build();

        menuRepository.save(menu);
    }
}
