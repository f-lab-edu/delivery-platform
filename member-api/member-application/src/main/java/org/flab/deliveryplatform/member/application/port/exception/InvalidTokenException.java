package org.flab.deliveryplatform.member.application.port.exception;

public class InvalidTokenException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "유효하지 않은 토큰 입니다.";

    public InvalidTokenException() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidTokenException(String message) {
        super(message);
    }
}
