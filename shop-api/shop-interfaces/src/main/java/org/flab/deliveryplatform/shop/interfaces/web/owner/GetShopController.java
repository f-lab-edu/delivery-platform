package org.flab.deliveryplatform.shop.interfaces.web.owner;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.shop.application.port.GetShopUseCase;
import org.flab.deliveryplatform.shop.application.port.dto.ShopDetailData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@OwnerShopRestController
public class GetShopController {

    private final GetShopUseCase getShopUseCase;

    @GetMapping("/{shopId}")
    public DeliveryPlatformResponse<ShopDetailData> getShop(@PathVariable Long shopId) {
        return DeliveryPlatformResponse.ok(getShopUseCase.getShop(shopId));
    }
}
