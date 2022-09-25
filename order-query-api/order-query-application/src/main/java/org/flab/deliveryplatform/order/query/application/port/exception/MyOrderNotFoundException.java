package org.flab.deliveryplatform.order.query.application.port.exception;

public class MyOrderNotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "나의 주문이 존재하지 않습니다.";

    public MyOrderNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public MyOrderNotFoundException(Long orderId) {
        super("주어진 식별자에 해당하는 나의 주문이 존재하지 않습니다. 문제의 식별자 = " + orderId);
    }
}
