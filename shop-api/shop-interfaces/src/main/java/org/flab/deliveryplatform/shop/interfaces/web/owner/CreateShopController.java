package org.flab.deliveryplatform.shop.interfaces.web.owner;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.shop.application.port.CreateShopUseCase;
import org.flab.deliveryplatform.shop.application.port.dto.CreateShopCommand;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@OwnerShopRestController
public class CreateShopController {

    private final CreateShopUseCase createShopUseCase;

    @PostMapping
    public DeliveryPlatformResponse<Void> createShop(@RequestBody CreateShopCommand command) {
        createShopUseCase.createShop(command);
        return DeliveryPlatformResponse.ok(null);
    }
}
