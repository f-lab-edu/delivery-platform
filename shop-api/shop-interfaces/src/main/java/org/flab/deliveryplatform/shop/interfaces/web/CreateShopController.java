package org.flab.deliveryplatform.shop.interfaces.web;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.shop.application.port.CreateShopUseCase;
import org.flab.deliveryplatform.shop.application.port.dto.CreateShopCommand;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/shops")
@RestController
public class CreateShopController {

    private final CreateShopUseCase createShopUseCase;

    @PostMapping
    public DeliveryPlatformResponse<Void> createShop(@RequestBody CreateShopCommand command) {
        createShopUseCase.createShop(command);
        return DeliveryPlatformResponse.ok(null);
    }
}
