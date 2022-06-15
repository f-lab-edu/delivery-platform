package org.flab.deliveryplatform.common.exception;

public class DeliveryPlatformException extends RuntimeException {

    private DeliveryPlatformErrorCode errorCode;

    public DeliveryPlatformException() {
        this(DeliveryPlatformDefaultErrorCode.BAD_REQUEST);
    }

    public DeliveryPlatformException(DeliveryPlatformErrorCode errorCode) {
        this(errorCode, errorCode.getMessage());

    }

    public DeliveryPlatformException(DeliveryPlatformErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
