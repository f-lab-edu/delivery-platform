package org.flab.deliveryplatform.shop.interfaces.web.owner;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.shop.application.port.CreateMenuUseCase;
import org.flab.deliveryplatform.shop.application.port.dto.CreateMenuCommand;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@OwnerShopRestController
public class CreateMenuController {

    private final CreateMenuUseCase createMenuUseCase;

    @PostMapping("/{shopId}/menus")
    public DeliveryPlatformResponse<Void> createMenu(
        @PathVariable Long shopId, @RequestBody CreateMenuCommand command) {
        createMenuUseCase.createMenu(shopId, command);
        return DeliveryPlatformResponse.ok(null);
    }
}
