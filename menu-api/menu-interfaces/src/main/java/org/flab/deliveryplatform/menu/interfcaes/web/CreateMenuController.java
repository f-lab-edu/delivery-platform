package org.flab.deliveryplatform.menu.interfcaes.web;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.menu.appliciation.port.CreateMenuUseCase;
import org.flab.deliveryplatform.menu.appliciation.port.dto.CreateMenuCommand;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CreateMenuController {

    private final CreateMenuUseCase createMenuUseCase;

    @PostMapping("/shops/{shopId}/menus")
    public DeliveryPlatformResponse<Void> createMenu(
        @PathVariable Long shopId, @RequestBody CreateMenuCommand command) {
        createMenuUseCase.createMenu(shopId, command);
        return DeliveryPlatformResponse.ok(null);
    }
}
