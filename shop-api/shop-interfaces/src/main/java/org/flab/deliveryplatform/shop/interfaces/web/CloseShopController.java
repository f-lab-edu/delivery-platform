package org.flab.deliveryplatform.shop.interfaces.web;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.shop.application.port.CloseShopUseCase;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/shops")
@RestController
public class CloseShopController {

    private final CloseShopUseCase closeShopUseCase;

    @DeleteMapping("/{shopId}")
    public DeliveryPlatformResponse<Void> closeShop(@PathVariable Long shopId) {
        closeShopUseCase.closeShop(shopId);
        return DeliveryPlatformResponse.ok(null);
    }
}
