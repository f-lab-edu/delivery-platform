package org.flab.deliveryplatform.order.interfaces.web.member;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.order.application.port.CancelOrderUseCase;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RequiredArgsConstructor
@MemberOrderRestController
public class CancelOrderController {

    private final CancelOrderUseCase cancelOrderUseCase;

    @PutMapping("/{orderId}/cancel")
    public DeliveryPlatformResponse<Void> payOrder(@PathVariable Long orderId) {
        cancelOrderUseCase.cancelOrder(orderId);
        return DeliveryPlatformResponse.ok(null);
    }
}
