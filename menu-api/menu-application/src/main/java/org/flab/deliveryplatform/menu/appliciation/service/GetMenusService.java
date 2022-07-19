package org.flab.deliveryplatform.menu.appliciation.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.domain.ShopId;
import org.flab.deliveryplatform.menu.appliciation.port.GetMenusUseCase;
import org.flab.deliveryplatform.menu.appliciation.port.MenuRepository;
import org.flab.deliveryplatform.menu.appliciation.port.dto.MenuData;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetMenusService implements GetMenusUseCase {

    private final MenuRepository menuRepository;

    @Override
    public List<MenuData> getMenus(Long shopId) {
        return menuRepository.findAllByShopId(new ShopId(shopId)).stream()
            .map(MenuData::from)
            .collect(Collectors.toList());
    }
}
