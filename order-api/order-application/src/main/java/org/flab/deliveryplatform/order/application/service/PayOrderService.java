package org.flab.deliveryplatform.order.application.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.event.EventPublisher;
import org.flab.deliveryplatform.common.event.OutBoxEvent;
import org.flab.deliveryplatform.order.application.port.OrderRepository;
import org.flab.deliveryplatform.order.application.port.OutBoxEventFactory;
import org.flab.deliveryplatform.order.application.port.PayOrderUseCase;
import org.flab.deliveryplatform.order.application.port.exception.OrderNotFoundException;
import org.flab.deliveryplatform.order.domain.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PayOrderService implements PayOrderUseCase {

    private final OrderRepository orderRepository;

    private final EventPublisher orderEventPublisher;

    private final OutBoxEventFactory<Order> outBoxEventFactory;

    @Transactional
    @Override
    public void payOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new OrderNotFoundException(orderId));

        order.payed();

        List<OutBoxEvent> outBoxEvents = outBoxEventFactory.createOutBoxEvents(order);
        orderEventPublisher.publishAll(outBoxEvents);
    }
}
