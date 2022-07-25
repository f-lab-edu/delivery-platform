package org.flab.deliveryplatform.shop.application.port.exception;

public class ShopNotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "매장이 존재하지 않습니다.";

    public ShopNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public ShopNotFoundException(Long shopId) {
        super("주어진 식별자에 해당하는 매장이 존재하지 않습니다. 문제의 식별자 = " + shopId);
    }
}
