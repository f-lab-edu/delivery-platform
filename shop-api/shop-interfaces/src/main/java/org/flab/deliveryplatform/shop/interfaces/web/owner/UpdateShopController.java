package org.flab.deliveryplatform.shop.interfaces.web.owner;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.shop.application.port.UpdateShopUseCase;
import org.flab.deliveryplatform.shop.application.port.dto.UpdateShopCommand;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@OwnerShopRestController
public class UpdateShopController {

    private final UpdateShopUseCase updateShopUseCase;

    @PutMapping("/{shopId}")
    public DeliveryPlatformResponse<Void> updateShop(
        @PathVariable Long shopId, @RequestBody UpdateShopCommand command) {
        updateShopUseCase.updateShop(shopId, command);
        return DeliveryPlatformResponse.ok(null);
    }
}
