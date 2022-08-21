package org.flab.deliveryplatform.order.domain.exception;

public class InvalidOrderStatusException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "유효하지 않은 주문 상태입니다.";

    public InvalidOrderStatusException() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidOrderStatusException(String message) {
        super(message);
    }
}
