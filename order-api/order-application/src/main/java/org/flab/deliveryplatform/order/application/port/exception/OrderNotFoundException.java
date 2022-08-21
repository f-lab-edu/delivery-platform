package org.flab.deliveryplatform.order.application.port.exception;

public class OrderNotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "주문이 존재하지 않습니다.";

    public OrderNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public OrderNotFoundException(Long orderId) {
        super("주어진 식별자에 해당하는 주문이 존재하지 않습니다. 문제의 식별자 = " + orderId);
    }
}
