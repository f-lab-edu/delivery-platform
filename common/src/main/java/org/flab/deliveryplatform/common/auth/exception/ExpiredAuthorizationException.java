package org.flab.deliveryplatform.common.auth.exception;

public class ExpiredAuthorizationException extends RuntimeException {

    public ExpiredAuthorizationException() {
        super();
    }

    public ExpiredAuthorizationException(String message) {
        super(message);
    }

    public ExpiredAuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExpiredAuthorizationException(Throwable cause) {
        super(cause);
    }

    protected ExpiredAuthorizationException(String message, Throwable cause, boolean enableSuppression,
        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
