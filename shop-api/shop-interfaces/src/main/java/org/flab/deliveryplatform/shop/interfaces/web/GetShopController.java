package org.flab.deliveryplatform.shop.interfaces.web;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.shop.application.port.GetShopUseCase;
import org.flab.deliveryplatform.shop.application.port.dto.ShopData;
import org.flab.deliveryplatform.shop.interfaces.web.common.ShopRestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@ShopRestController
public class GetShopController {

    private final GetShopUseCase getShopUseCase;

    @GetMapping("/{shopId}")
    public DeliveryPlatformResponse<ShopData> getShop(@PathVariable Long shopId) {
        return DeliveryPlatformResponse.ok(getShopUseCase.getShop(shopId));
    }
}
