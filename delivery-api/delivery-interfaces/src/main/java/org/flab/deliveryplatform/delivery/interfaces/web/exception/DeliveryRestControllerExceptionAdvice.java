package org.flab.deliveryplatform.delivery.interfaces.web.exception;

import static org.flab.deliveryplatform.delivery.interfaces.web.exception.DeliveryErrorCode.DELIVERY_NOT_FOUND;

import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformErrorResponse;
import org.flab.deliveryplatform.delivery.application.port.exception.DeliveryNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(value = Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class DeliveryRestControllerExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DeliveryNotFoundException.class)
    public DeliveryPlatformErrorResponse<Object> handleDeliveryNotFoundException(DeliveryNotFoundException e) {
        return DeliveryPlatformErrorResponse.error(null, DELIVERY_NOT_FOUND.name(), e.getMessage());
    }
}
