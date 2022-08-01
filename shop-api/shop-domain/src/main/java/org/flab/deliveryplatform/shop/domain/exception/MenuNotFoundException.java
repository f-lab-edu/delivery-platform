package org.flab.deliveryplatform.shop.domain.exception;

public class MenuNotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "메뉴가 존재하지 않습니다.";

    public MenuNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public MenuNotFoundException(Long shopId) {
        super("주어진 식별자에 해당하는 메뉴가 존재하지 않습니다. 문제의 식별자 = " + shopId);
    }
}
