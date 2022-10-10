package org.flab.deliveryplatform.order.query.application.service;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.order.query.application.port.GetShopRepository;
import org.flab.deliveryplatform.order.query.application.port.MyOrderRepository;
import org.flab.deliveryplatform.order.query.application.port.SyncMyOrderUseCase;
import org.flab.deliveryplatform.order.query.application.port.dto.SyncMyOrderCommand;
import org.flab.deliveryplatform.order.query.application.port.exception.MyOrderNotFoundException;
import org.flab.deliveryplatform.order.query.domain.MyOrder;
import org.flab.deliveryplatform.order.query.domain.MyOrder.MyOrderLineItem;
import org.flab.deliveryplatform.order.query.domain.Shop;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SyncMyOrderService implements SyncMyOrderUseCase {

    private final MyOrderRepository myOrderRepository;

    private final GetShopRepository getShopRepository;

    @Transactional
    @Override
    public void syncMyOrder(SyncMyOrderCommand command) {
        Shop shop = getShopRepository.findById(command.getShopId())
            .orElseThrow(() -> new IllegalArgumentException("가게가 존재하지 않습니다."));

        MyOrder myOrder = MyOrder.builder()
            .memberId(command.getMemberId())
            .orderId(command.getOrderId())
            .shopName(shop.getName())
            .status(command.getOrderStatus())
            .totalPrice(command.getOrderTotalPrice())
            .myOrderLineItems(
                command.getOrderLineItems().stream()
                    .map(ol -> new MyOrderLineItem(ol.getName(), ol.getCount(), ol.getTotalPrice()))
                    .collect(Collectors.toList()))
            .deliveryStatus(null)
            .build();

        myOrderRepository.save(myOrder);
    }

    @Transactional
    @Override
    public void syncMyOrderStatus(Long orderId, String status) {
        MyOrder myOrder = findMyOrder(orderId);
        myOrder.changeStatus(status);
    }

    @Transactional
    @Override
    public void syncMyOrderDeliveryStatus(Long orderId, String deliveryStatus) {
        MyOrder myOrder = findMyOrder(orderId);
        myOrder.changeDeliveryStatus(deliveryStatus);
    }

    private MyOrder findMyOrder(Long orderId) {
        return myOrderRepository.findByOrderId(orderId)
            .orElseThrow(() -> new MyOrderNotFoundException(orderId));
    }
}
