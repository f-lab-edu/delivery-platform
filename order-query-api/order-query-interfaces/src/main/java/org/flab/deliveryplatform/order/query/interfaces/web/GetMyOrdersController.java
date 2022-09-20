package org.flab.deliveryplatform.order.query.interfaces.web;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.order.query.application.GetMyOrdersQuery;
import org.flab.deliveryplatform.order.query.application.dto.OrderData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GetMyOrdersController {

    private final GetMyOrdersQuery getMyOrdersQuery;

    @GetMapping("/members/orders/me")
    public DeliveryPlatformResponse<List<OrderData>> getMyOrders() {
        // TODO: 로그인 된 회원 정보로 조회하기.
        return DeliveryPlatformResponse.ok(getMyOrdersQuery.getMyOrders(1L));
    }
}
