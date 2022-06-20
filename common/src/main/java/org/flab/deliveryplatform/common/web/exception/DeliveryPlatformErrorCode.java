package org.flab.deliveryplatform.common.web.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeliveryPlatformErrorCode {
    BAD_REQUEST("잘못된 요청입니다.");

    private String message;
}
