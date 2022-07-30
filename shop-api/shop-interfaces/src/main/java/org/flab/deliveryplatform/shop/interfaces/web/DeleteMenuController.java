package org.flab.deliveryplatform.shop.interfaces.web;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.shop.application.port.DeleteMenuUseCase;
import org.flab.deliveryplatform.shop.interfaces.web.common.ShopRestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@ShopRestController
public class DeleteMenuController {

    private final DeleteMenuUseCase deleteMenuUseCase;

    @DeleteMapping("/{shopId}/menus/{menuId}")
    public DeliveryPlatformResponse<Void> deleteMenu(@PathVariable Long shopId, @PathVariable Long menuId) {
        deleteMenuUseCase.deleteMenu(shopId, menuId);
        return DeliveryPlatformResponse.ok(null);
    }
}
