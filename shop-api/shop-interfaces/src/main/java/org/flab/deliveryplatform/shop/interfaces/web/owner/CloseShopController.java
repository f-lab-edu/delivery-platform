package org.flab.deliveryplatform.shop.interfaces.web.owner;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.auth.OwnerOnly;
import org.flab.deliveryplatform.common.auth.User;
import org.flab.deliveryplatform.common.auth.UserInfo;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.shop.application.port.CloseShopUseCase;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@OwnerOnly
@RequiredArgsConstructor
@OwnerShopRestController
public class CloseShopController {

    private final CloseShopUseCase closeShopUseCase;

    @PutMapping("/{shopId}/close")
    public DeliveryPlatformResponse<Void> closeShop(@UserInfo User user, @PathVariable Long shopId) {
        closeShopUseCase.closeShop(shopId, user.getUserId());
        return DeliveryPlatformResponse.ok(null);
    }
}
