package org.flab.deliveryplatform.owner.application.port.exception;

public class InvalidOwnerInfoException extends RuntimeException {

    public InvalidOwnerInfoException() {
        super("잘못된 회원 정보입니다.");
    }

    public InvalidOwnerInfoException(String message) {
        super(message);
    }

    public InvalidOwnerInfoException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidOwnerInfoException(Throwable cause) {
        super(cause);
    }

    protected InvalidOwnerInfoException(String message, Throwable cause, boolean enableSuppression,
        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
