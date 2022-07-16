package org.flab.deliveryplatform.shop.interfaces.web;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.shop.application.port.GetShopsUseCase;
import org.flab.deliveryplatform.shop.application.port.dto.ShopData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/shops")
@RestController
public class GetShopsController {

    private final GetShopsUseCase getShopsUseCase;

    @GetMapping
    public DeliveryPlatformResponse<List<ShopData>> getShops() {
        return DeliveryPlatformResponse.ok(getShopsUseCase.getShops());
    }
}
