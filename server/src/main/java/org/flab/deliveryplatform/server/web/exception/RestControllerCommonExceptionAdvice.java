package org.flab.deliveryplatform.server.web.exception;

import static org.flab.deliveryplatform.common.web.exception.DeliveryPlatformErrorCode.BAD_REQUEST;

import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformErrorResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestControllerCommonExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DeliveryPlatformErrorResponse<Object>> defaultExHandler() {
        return ResponseEntity.badRequest().body(
            DeliveryPlatformErrorResponse.error(null, BAD_REQUEST.name(),
                BAD_REQUEST.getMessage()));
    }
}

