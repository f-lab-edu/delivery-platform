package org.flab.deliveryplatform.delivery.application.port.exception;

public class DeliveryNotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "배달이 존재하지 않습니다.";

    public DeliveryNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public DeliveryNotFoundException(Long deliveryId) {
        super("주어진 식별자에 해당하는 배달이 존재하지 않습니다. 문제의 식별자 = " + deliveryId);
    }
}
