package org.flab.deliveryplatform.menu.interfcaes.web;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.menu.appliciation.port.GetMenusUseCase;
import org.flab.deliveryplatform.menu.appliciation.port.dto.MenuData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GetMenusController {

    private final GetMenusUseCase getMenusUseCase;

    @GetMapping("/shops/{shopId}/menus")
    public DeliveryPlatformResponse<List<MenuData>> getMenus(@PathVariable Long shopId) {
        return DeliveryPlatformResponse.ok(getMenusUseCase.getMenus(shopId));
    }
}
