package org.flab.deliveryplatform.order.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.flab.deliveryplatform.order.domain.exception.InvalidOrderStatusException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderTest {

    private Order order;
    private Order deliveredOrder;

    @BeforeEach
    void setUp() {
        order = Order.builder()
            .status(OrderStatus.ORDERED)
            .build();

        deliveredOrder = Order.builder()
            .status(OrderStatus.DELIVERED)
            .build();
    }

    @Test
    void cancelOrder() {
        order.cancel();

        assertThat(order.getStatus()).isEqualTo(OrderStatus.CANCELED);
    }

    @Test
    void cancelDeliveredOrder() {
        assertThatThrownBy(() -> deliveredOrder.cancel())
            .isInstanceOf(InvalidOrderStatusException.class);
    }
}
