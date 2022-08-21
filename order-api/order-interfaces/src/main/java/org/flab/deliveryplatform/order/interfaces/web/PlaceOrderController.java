package org.flab.deliveryplatform.order.interfaces.web;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.order.application.port.PlaceOrderUseCase;
import org.flab.deliveryplatform.order.application.port.dto.PlaceOrderCommand;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/orders")
@RestController
public class PlaceOrderController {

    private final PlaceOrderUseCase placeOrderUseCase;

    @PostMapping
    public DeliveryPlatformResponse<Void> placeOrder(@RequestBody PlaceOrderCommand command) {
        placeOrderUseCase.placeOrder(command);
        return DeliveryPlatformResponse.ok(null);
    }
}
