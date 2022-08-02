package org.flab.deliveryplatform.shop.interfaces.web;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.shop.application.port.DeleteOptionGroupUseCase;
import org.flab.deliveryplatform.shop.interfaces.web.common.ShopRestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@ShopRestController
public class DeleteOptionGroupController {

    private final DeleteOptionGroupUseCase deleteOptionGroupUseCase;

    @DeleteMapping("/{shopId}/menus/{menuId}/optionGroups/{optionGroupId}")
    public DeliveryPlatformResponse<Void> deleteOptionGroup(
        @PathVariable Long shopId, @PathVariable Long menuId, @PathVariable Long optionGroupId) {
        deleteOptionGroupUseCase.deleteOptionGroup(shopId, menuId, optionGroupId);
        return DeliveryPlatformResponse.ok(null);
    }
}
