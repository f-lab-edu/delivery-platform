package org.flab.deliveryplatform.shop.domain.exception;

public class DuplicatedOptionGroupNameException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "주어진 이름에 해당하는 옵션 그륩이 이미 존재합니다.";

    public DuplicatedOptionGroupNameException() {
        super(DEFAULT_MESSAGE);
    }

    public DuplicatedOptionGroupNameException(String name) {
        super("주어진 이름에 해당하는 옵션 그륩이 이미 존재합니다. 문제의 이름 = " + name);
    }
}
