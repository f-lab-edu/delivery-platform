package org.flab.deliveryplatform.order.application.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.order.application.port.GetMyOrdersQuery;
import org.flab.deliveryplatform.order.application.port.OrderQueryRepository;
import org.flab.deliveryplatform.order.application.port.dto.OrderData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GetMyOrdersService implements GetMyOrdersQuery {

    private final OrderQueryRepository orderQueryRepository;

    @Transactional(readOnly = true)
    @Override
    public List<OrderData> getMyOrders(Long memberId) {
        return orderQueryRepository.findAllByMemberId(memberId);
    }
}
