package org.flab.deliveryplatform.shop.interfaces.web.owner;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.auth.OwnerOnly;
import org.flab.deliveryplatform.common.auth.User;
import org.flab.deliveryplatform.common.auth.UserInfo;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.shop.application.port.DeleteOptionGroupUseCase;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@OwnerOnly
@RequiredArgsConstructor
@OwnerShopRestController
public class DeleteOptionGroupController {

    private final DeleteOptionGroupUseCase deleteOptionGroupUseCase;

    @DeleteMapping("/{shopId}/menus/{menuId}/optionGroups/{optionGroupId}")
    public DeliveryPlatformResponse<Void> deleteOptionGroup(
        @UserInfo User user, @PathVariable Long shopId, @PathVariable Long menuId, @PathVariable Long optionGroupId) {
        deleteOptionGroupUseCase.deleteOptionGroup(shopId, user.getUserId(), menuId, optionGroupId);
        return DeliveryPlatformResponse.ok(null);
    }
}
