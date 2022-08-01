package org.flab.deliveryplatform.shop.interfaces.web;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.shop.application.port.CloseShopUseCase;
import org.flab.deliveryplatform.shop.interfaces.web.common.ShopRestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RequiredArgsConstructor
@ShopRestController
public class CloseShopController {

    private final CloseShopUseCase closeShopUseCase;

    @PutMapping("/{shopId}/close")
    public DeliveryPlatformResponse<Void> closeShop(@PathVariable Long shopId) {
        closeShopUseCase.closeShop(shopId);
        return DeliveryPlatformResponse.ok(null);
    }
}
