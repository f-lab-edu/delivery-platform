package org.flab.deliveryplatform.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeliveryPlatformDefaultErrorCode implements DeliveryPlatformErrorCode {
    BAD_REQUEST("잘못된 요청입니다.");

    private String message;
}
