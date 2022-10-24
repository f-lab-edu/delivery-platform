package org.flab.deliveryplatform.order.application.service;

import java.util.ArrayList;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.flab.deliveryplatform.common.event.EventPublisher;
import org.flab.deliveryplatform.order.application.port.OrderRepository;
import org.flab.deliveryplatform.order.domain.Order;
import org.flab.deliveryplatform.order.domain.OrderStatus;
import org.flab.deliveryplatform.order.domain.event.OrderPayedEvent;
import org.flab.deliveryplatform.order.domain.event.OrderPayedNotificationEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@Slf4j
@ExtendWith(MockitoExtension.class)
class PayOrderServiceTest {

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    PayOrderService payOrderService;

    Order order;

    @Spy
    EventPublisher eventPublisher;

    @BeforeEach
    void init() {
        order = Order.builder()
            .id(0L)
            .memberId(0L)
            .shopId(0L)
            .orderLineItems(new ArrayList<>())
            .status(OrderStatus.ORDERED)
            .build();
    }

    @Test
    void test() {
        BDDMockito.given(orderRepository.findById(order.getId()))
            .willReturn(Optional.ofNullable(order));

        BDDMockito.doAnswer(invocation -> {
            OrderPayedEvent orderPayedEvent = invocation.getArgument(0);
            Assertions.assertThat(orderPayedEvent.getOrderId()).isEqualTo(order.getId());
            return null;
        }).when(eventPublisher).publish(BDDMockito.any(OrderPayedEvent.class));

        BDDMockito.doAnswer(invocation -> {
            OrderPayedNotificationEvent orderPayedNotificationEvent = invocation.getArgument(0);
            Assertions.assertThat(orderPayedNotificationEvent.getOrderId()).isEqualTo(order.getId());
            Assertions.assertThat(orderPayedNotificationEvent.getShopId()).isEqualTo(order.getShopId());
            return null;
        }).when(eventPublisher).publish(BDDMockito.any(OrderPayedNotificationEvent.class));

        payOrderService.payOrder(order.getId());
    }
}
