package org.flab.deliveryplatform.shop.interfaces.web.owner;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.auth.OwnerOnly;
import org.flab.deliveryplatform.common.auth.User;
import org.flab.deliveryplatform.common.auth.UserInfo;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.shop.application.port.DeleteOptionUseCase;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@OwnerOnly
@RequiredArgsConstructor
@OwnerShopRestController
public class DeleteOptionController {

    private final DeleteOptionUseCase deleteOptionUseCase;

    @DeleteMapping("/{shopId}/menus/{menuId}/optionGroups/{optionGroupId}/options/{optionId}")
    public DeliveryPlatformResponse<Void> deleteOption(
        @UserInfo User user, @PathVariable Long shopId, @PathVariable Long menuId,
        @PathVariable Long optionGroupId, @PathVariable Long optionId) {
        deleteOptionUseCase.deleteOption(shopId, user.getUserId(), menuId, optionGroupId, optionId);
        return DeliveryPlatformResponse.ok(null);
    }
}
