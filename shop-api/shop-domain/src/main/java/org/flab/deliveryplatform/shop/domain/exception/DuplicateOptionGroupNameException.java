package org.flab.deliveryplatform.shop.domain.exception;

public class DuplicateOptionGroupNameException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "주어진 이름에 해당하는 옵션 그륩이 이미 존재합니다.";

    public DuplicateOptionGroupNameException() {
        super(DEFAULT_MESSAGE);
    }

    public DuplicateOptionGroupNameException(String name) {
        super("주어진 이름에 해당하는 옵션 그륩이 이미 존재합니다. 문제의 이름 = " + name);
    }
}
