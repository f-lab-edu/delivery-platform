package org.flab.deliveryplatform.shop.interfaces.web.owner;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.shop.application.port.CloseShopUseCase;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RequiredArgsConstructor
@OwnerShopRestController
public class CloseShopController {

    private final CloseShopUseCase closeShopUseCase;

    @PutMapping("/{shopId}/close")
    public DeliveryPlatformResponse<Void> closeShop(@PathVariable Long shopId) {
        closeShopUseCase.closeShop(shopId);
        return DeliveryPlatformResponse.ok(null);
    }
}
