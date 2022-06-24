package org.flab.deliveryplatform.common.web.dto;

import lombok.Getter;

@Getter
public class DeliveryPlatformResponse<T> {

    private boolean success;
    private T data;

    public DeliveryPlatformResponse(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public DeliveryPlatformResponse(T data) {
        this(true, data);
    }

    public static <T> DeliveryPlatformResponse<T> ok(T data) {
        return new DeliveryPlatformResponse<>(data);
    }
}
