package org.flab.deliveryplatform.order.interfaces.web.exception;

import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformErrorResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(value = Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class OrderRestControllerExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalStateException.class)
    public DeliveryPlatformErrorResponse<Object> handleIllegalStateException(IllegalStateException e) {
        return DeliveryPlatformErrorResponse.error(null, "ORDER_VALIDATE_ERROR", e.getMessage());
    }
}
