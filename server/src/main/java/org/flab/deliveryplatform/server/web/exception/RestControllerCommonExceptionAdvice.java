package org.flab.deliveryplatform.server.web.exception;

import static org.flab.deliveryplatform.common.web.exception.DeliveryPlatformErrorCode.BAD_REQUEST;

import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerCommonExceptionAdvice {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<DeliveryPlatformErrorResponse<Object>> handleIllegalStateException(IllegalStateException ex) {
        return ResponseEntity.badRequest().body(
            DeliveryPlatformErrorResponse.error(null, BAD_REQUEST.name(),
                BAD_REQUEST.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DeliveryPlatformErrorResponse<Object>> defaultExHandler(Exception ex) {
        return ResponseEntity.badRequest().body(
            DeliveryPlatformErrorResponse.error(null, BAD_REQUEST.name(),
                BAD_REQUEST.getMessage()));
    }
}
