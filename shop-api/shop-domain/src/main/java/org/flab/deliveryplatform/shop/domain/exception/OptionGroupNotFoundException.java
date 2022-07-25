package org.flab.deliveryplatform.shop.domain.exception;

public class OptionGroupNotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "옵션 그륩이 존재하지 않습니다.";

    public OptionGroupNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public OptionGroupNotFoundException(Long optionGroupId) {
        super("주어진 식별자에 해당하는 옵션 그륩이 존재하지 않습니다. 문제의 식별자 = " + optionGroupId);
    }
}
