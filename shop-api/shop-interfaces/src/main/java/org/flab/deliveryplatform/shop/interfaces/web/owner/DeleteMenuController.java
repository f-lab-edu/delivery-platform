package org.flab.deliveryplatform.shop.interfaces.web.owner;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.auth.User;
import org.flab.deliveryplatform.common.auth.UserInfo;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.shop.application.port.DeleteMenuUseCase;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@OwnerShopRestController
public class DeleteMenuController {

    private final DeleteMenuUseCase deleteMenuUseCase;

    @DeleteMapping("/{shopId}/menus/{menuId}")
    public DeliveryPlatformResponse<Void> deleteMenu(@UserInfo User user, @PathVariable Long shopId,
        @PathVariable Long menuId) {
        deleteMenuUseCase.deleteMenu(shopId, user.getUserId(), menuId);
        return DeliveryPlatformResponse.ok(null);
    }
}
