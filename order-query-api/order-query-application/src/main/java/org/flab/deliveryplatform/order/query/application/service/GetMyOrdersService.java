package org.flab.deliveryplatform.order.query.application.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.order.query.application.port.GetMyOrdersQuery;
import org.flab.deliveryplatform.order.query.application.port.MyOrderRepository;
import org.flab.deliveryplatform.order.query.application.port.dto.OrderData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GetMyOrdersService implements GetMyOrdersQuery {

    private final MyOrderRepository myOrderRepository;
    
    @Transactional(readOnly = true)
    @Override
    public List<OrderData> getMyOrders(Long memberId) {
        return myOrderRepository.findAllByMemberId(memberId);
    }
}
