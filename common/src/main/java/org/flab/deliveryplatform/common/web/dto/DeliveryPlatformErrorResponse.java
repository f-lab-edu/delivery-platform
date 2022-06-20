package org.flab.deliveryplatform.common.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeliveryPlatformErrorResponse<T> extends DeliveryPlatformResponse<T> {

    private DeliveryPlatformErrorResult errorResult;

    private DeliveryPlatformErrorResponse(T data, String errorCode, String message) {
        super(false, data);
        errorResult = new DeliveryPlatformErrorResult(errorCode, message);
    }

    public static <T> DeliveryPlatformErrorResponse<T> error(T data, String errorCode,
        String message) {
        return new DeliveryPlatformErrorResponse<>(data, errorCode, message);
    }

    @Getter
    @NoArgsConstructor
    public static class DeliveryPlatformErrorResult {

        private String errorCode;
        private String message;

        private DeliveryPlatformErrorResult(String errorCode, String message) {
            this.errorCode = errorCode;
            this.message = message;
        }
    }
}