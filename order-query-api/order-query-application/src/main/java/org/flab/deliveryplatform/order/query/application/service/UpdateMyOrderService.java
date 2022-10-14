package org.flab.deliveryplatform.order.query.application.service;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.order.query.application.port.MyOrderRepository;
import org.flab.deliveryplatform.order.query.application.port.UpdateMyOrderUseCase;
import org.flab.deliveryplatform.order.query.application.port.exception.MyOrderNotFoundException;
import org.flab.deliveryplatform.order.query.domain.MyOrder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UpdateMyOrderService implements UpdateMyOrderUseCase {

    private final MyOrderRepository myOrderRepository;
    
    @Transactional
    @Override
    public void updateMyOrderStatus(Long orderId, String status) {
        MyOrder myOrder = findMyOrder(orderId);
        myOrder.changeStatus(status);
    }

    @Transactional
    @Override
    public void updateMyOrderDeliveryStatus(Long orderId, String deliveryStatus) {
        MyOrder myOrder = findMyOrder(orderId);
        myOrder.changeDeliveryStatus(deliveryStatus);
    }

    private MyOrder findMyOrder(Long orderId) {
        return myOrderRepository.findByOrderId(orderId)
            .orElseThrow(() -> new MyOrderNotFoundException(orderId));
    }
}
