package org.flab.deliveryplatform.server.web.filter;

public class NotSupportedURIException extends RuntimeException {

    public NotSupportedURIException() {
        super();
    }

    public NotSupportedURIException(String message) {
        super(message);
    }

    public NotSupportedURIException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotSupportedURIException(Throwable cause) {
        super(cause);
    }

    protected NotSupportedURIException(String message, Throwable cause, boolean enableSuppression,
        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
