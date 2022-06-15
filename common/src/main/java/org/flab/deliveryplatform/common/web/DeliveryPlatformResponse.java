package org.flab.deliveryplatform.common.web;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DeliveryPlatformResponse<T> {

    private boolean success;
    private T data;

    protected DeliveryPlatformResponse(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    private DeliveryPlatformResponse(T data) {
        this(true, data);
    }

    public static <T> DeliveryPlatformResponse<T> ok(T data) {
        return new DeliveryPlatformResponse<>(data);
    }
}
