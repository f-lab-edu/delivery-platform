package org.flab.deliveryplatform.order.query.interfaces.eventlistener;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.order.query.application.port.SyncMyOrderUseCase;
import org.flab.deliveryplatform.order.query.application.port.dto.SyncMyOrderCommand;
import org.flab.deliveryplatform.order.query.application.port.dto.SyncMyOrderCommand.OrderLineItem;
import org.flab.deliveryplatform.order.query.interfaces.eventlistener.event.OrderCreatedApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderCreatedApplicationEventListener {

    private final SyncMyOrderUseCase syncMyOrderUseCase;

    @EventListener
    public void handle(OrderCreatedApplicationEvent event) {
        SyncMyOrderCommand command = SyncMyOrderCommand.builder()
            .orderId(event.getOrderId())
            .shopId(event.getShopId())
            .memberId(event.getMemberId())
            .orderStatus(event.getOrderStatus())
            .orderTotalPrice(event.getOrderTotalPrice())
            .orderLineItems(
                event.getOrderLineItems().stream()
                    .map((ol) -> new OrderLineItem(ol.getName(), ol.getCount(), ol.getTotalPrice()))
                    .collect(Collectors.toList()))
            .build();

        syncMyOrderUseCase.syncMyOrder(command);
    }
}
