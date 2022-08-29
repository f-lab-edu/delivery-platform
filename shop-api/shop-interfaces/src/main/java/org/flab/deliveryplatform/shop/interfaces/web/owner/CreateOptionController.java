package org.flab.deliveryplatform.shop.interfaces.web.owner;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.shop.application.port.CreateOptionUseCase;
import org.flab.deliveryplatform.shop.application.port.dto.OptionCommand;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@OwnerShopRestController
public class CreateOptionController {

    private final CreateOptionUseCase createOptionUseCase;

    @PostMapping("/{shopId}/menus/{menuId}/optionGroups/{optionGroupId}/options")
    public DeliveryPlatformResponse<Void> createOptionGroup(
        @PathVariable Long shopId, @PathVariable Long menuId, @PathVariable Long optionGroupId,
        @RequestBody OptionCommand command) {
        createOptionUseCase.createOption(shopId, menuId, optionGroupId, command);
        return DeliveryPlatformResponse.ok(null);
    }
}
