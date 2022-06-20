package org.flab.deliveryplatform.member.application.port.exception;

public class DuplicatedEmailException extends RuntimeException {

    public DuplicatedEmailException() {
        super("중복된 이메일 입니다.");
    }

    public DuplicatedEmailException(String message) {
        super(message);
    }

    public DuplicatedEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicatedEmailException(Throwable cause) {
        super(cause);
    }

    public DuplicatedEmailException(String message, Throwable cause, boolean enableSuppression,
        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
