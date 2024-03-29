package org.flab.deliveryplatform.shop.domain.exception;

public class OptionNotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "옵션이 존재하지 않습니다.";

    public OptionNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public OptionNotFoundException(Long optionId) {
        super("주어진 식별자에 해당하는 옵션이 존재하지 않습니다. 문제의 식별자 = " + optionId);
    }
}
