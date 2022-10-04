package org.flab.deliveryplatform.order.query.application.service;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.order.query.application.port.CreateMyOrderUseCase;
import org.flab.deliveryplatform.order.query.application.port.GetShopRepository;
import org.flab.deliveryplatform.order.query.application.port.MyOrderRepository;
import org.flab.deliveryplatform.order.query.application.port.dto.CreateMyOrderCommand;
import org.flab.deliveryplatform.order.query.domain.MyOrder;
import org.flab.deliveryplatform.order.query.domain.MyOrder.MyOrderLineItem;
import org.flab.deliveryplatform.order.query.domain.Shop;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateMyOrderService implements CreateMyOrderUseCase {

    private final MyOrderRepository myOrderRepository;

    private final GetShopRepository getShopRepository;

    @Transactional
    @Override
    public void createMyOrder(CreateMyOrderCommand command) {
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
}
