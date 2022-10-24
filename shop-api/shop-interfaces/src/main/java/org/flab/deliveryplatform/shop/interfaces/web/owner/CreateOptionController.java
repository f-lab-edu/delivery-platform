package org.flab.deliveryplatform.shop.interfaces.web.owner;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.auth.OwnerOnly;
import org.flab.deliveryplatform.common.auth.User;
import org.flab.deliveryplatform.common.auth.UserInfo;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.shop.application.port.CreateOptionUseCase;
import org.flab.deliveryplatform.shop.application.port.dto.OptionCommand;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@OwnerOnly
@RequiredArgsConstructor
@OwnerShopRestController
public class CreateOptionController {

    private final CreateOptionUseCase createOptionUseCase;

    @PostMapping("/{shopId}/menus/{menuId}/optionGroups/{optionGroupId}/options")
    public DeliveryPlatformResponse<Void> createOptionGroup(
        @UserInfo User user, @PathVariable Long shopId, @PathVariable Long menuId, @PathVariable Long optionGroupId,
        @RequestBody OptionCommand command) {
        createOptionUseCase.createOption(shopId, user.getUserId(), menuId, optionGroupId, command);
        return DeliveryPlatformResponse.ok(null);
    }
}
