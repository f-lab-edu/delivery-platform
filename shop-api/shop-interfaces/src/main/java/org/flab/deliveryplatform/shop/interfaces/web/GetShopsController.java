package org.flab.deliveryplatform.shop.interfaces.web;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.shop.application.port.GetShopsUseCase;
import org.flab.deliveryplatform.shop.application.port.dto.ShopData;
import org.flab.deliveryplatform.shop.interfaces.web.common.ShopRestController;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@ShopRestController
public class GetShopsController {

    private final GetShopsUseCase getShopsUseCase;

    @GetMapping
    public DeliveryPlatformResponse<List<ShopData>> getShops() {
        return DeliveryPlatformResponse.ok(getShopsUseCase.getShops());
    }
}
