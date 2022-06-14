package org.flab.deliveryplatform.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeliveryPlatformDefaultErrorCode implements DeliveryPlatformErrorCode {
    DELIVERY_PLATFORM_DEFAULT_ERROR_CODE("DELIVERY_PLATFORM_DEFAULT_ERROR_CODE");

    private String message;
}
