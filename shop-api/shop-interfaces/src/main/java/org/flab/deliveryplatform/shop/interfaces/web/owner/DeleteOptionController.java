package org.flab.deliveryplatform.shop.interfaces.web.owner;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.shop.application.port.DeleteOptionUseCase;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@OwnerShopRestController
public class DeleteOptionController {

    private final DeleteOptionUseCase deleteOptionUseCase;

    @DeleteMapping("/{shopId}/menus/{menuId}/optionGroups/{optionGroupId}/options/{optionId}")
    public DeliveryPlatformResponse<Void> deleteOption(@PathVariable Long shopId, @PathVariable Long menuId,
        @PathVariable Long optionGroupId, @PathVariable Long optionId) {
        deleteOptionUseCase.deleteOption(shopId, menuId, optionGroupId, optionId);
        return DeliveryPlatformResponse.ok(null);
    }
}
