package org.flab.deliveryplatform.member.application.port.exception;

public class InvalidMemberInfoException extends RuntimeException {

    public InvalidMemberInfoException() {
        super("잘못된 회원 정보입니다.");
    }

    public InvalidMemberInfoException(String message) {
        super(message);
    }

    public InvalidMemberInfoException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidMemberInfoException(Throwable cause) {
        super(cause);
    }

    public InvalidMemberInfoException(String message, Throwable cause, boolean enableSuppression,
        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
