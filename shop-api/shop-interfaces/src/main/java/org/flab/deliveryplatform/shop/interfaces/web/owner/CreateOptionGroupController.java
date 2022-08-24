package org.flab.deliveryplatform.shop.interfaces.web.owner;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.shop.application.port.CreateOptionGroupUseCase;
import org.flab.deliveryplatform.shop.application.port.dto.CreateOptionGroupCommand;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@OwnerShopRestController
public class CreateOptionGroupController {

    private final CreateOptionGroupUseCase createOptionGroupUseCase;

    @PostMapping("/{shopId}/menus/{menuId}/optionGroups")
    public DeliveryPlatformResponse<Void> createOptionGroup(
        @PathVariable Long shopId, @PathVariable Long menuId, @RequestBody CreateOptionGroupCommand command) {
        createOptionGroupUseCase.createOptionGroup(shopId, menuId, command);
        return DeliveryPlatformResponse.ok(null);
    }
}
