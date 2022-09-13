package org.flab.deliveryplatform.order.interfaces.web.member;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.order.application.port.GetMyOrdersQuery;
import org.flab.deliveryplatform.order.application.port.dto.OrderData;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@MemberOrderRestController
public class GetMyOrdersController {

    private final GetMyOrdersQuery getMyOrdersQuery;

    @GetMapping("/me")
    public DeliveryPlatformResponse<List<OrderData>> getMyOrders() {
        // TODO: 로그인 된 회원 정보로 조회하기.
        return DeliveryPlatformResponse.ok(getMyOrdersQuery.getMyOrders(1L));
    }
}
