package org.flab.deliveryplatform.order.interfaces.web.member;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.order.application.port.PayOrderUseCase;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RequiredArgsConstructor
@MemberOrderRestController
public class PayOrderController {

    private final PayOrderUseCase payOrderUseCase;

    @PutMapping("/{orderId}/pay")
    public DeliveryPlatformResponse<Void> payOrder(@PathVariable Long orderId) {
        payOrderUseCase.payOrder(orderId);
        return DeliveryPlatformResponse.ok(null);
    }
}
