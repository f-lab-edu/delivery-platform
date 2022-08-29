package org.flab.deliveryplatform.order.interfaces.web.member;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.order.application.port.PlaceOrderUseCase;
import org.flab.deliveryplatform.order.application.port.dto.PlaceOrderCommand;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@MemberOrderRestController
public class PlaceOrderController {

    private final PlaceOrderUseCase placeOrderUseCase;

    @PostMapping
    public DeliveryPlatformResponse<Void> placeOrder(@RequestBody PlaceOrderCommand command) {
        placeOrderUseCase.placeOrder(command);
        return DeliveryPlatformResponse.ok(null);
    }
}
