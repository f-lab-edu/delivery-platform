package org.flab.deliveryplatform.server.sync.myorder.eventlistener;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.order.query.application.port.AddMyOrderUseCase;
import org.flab.deliveryplatform.order.query.application.port.dto.AddMyOrderCommand;
import org.flab.deliveryplatform.order.query.application.port.dto.AddMyOrderCommand.OrderLineItem;
import org.flab.deliveryplatform.server.sync.myorder.eventlistener.event.OrderCreatedApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderCreatedApplicationEventListener {

    private final AddMyOrderUseCase addMyOrderUseCase;

    @EventListener
    public void handle(OrderCreatedApplicationEvent event) {
        AddMyOrderCommand command = AddMyOrderCommand.builder()
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

        addMyOrderUseCase.addMyOrder(command);
    }
}
