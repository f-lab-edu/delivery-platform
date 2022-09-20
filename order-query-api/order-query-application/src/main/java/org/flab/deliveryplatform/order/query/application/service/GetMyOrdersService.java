package org.flab.deliveryplatform.order.query.application.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.order.query.application.GetMyOrdersQuery;
import org.flab.deliveryplatform.order.query.application.OrderQueryRepository;
import org.flab.deliveryplatform.order.query.application.dto.OrderData;
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
