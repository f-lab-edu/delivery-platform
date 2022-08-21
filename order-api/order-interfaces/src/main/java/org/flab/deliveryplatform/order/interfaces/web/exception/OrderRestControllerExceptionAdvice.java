package org.flab.deliveryplatform.order.interfaces.web.exception;

import static org.flab.deliveryplatform.order.interfaces.web.exception.OrderErrorCode.INVALID_ORDER_STATUS;
import static org.flab.deliveryplatform.order.interfaces.web.exception.OrderErrorCode.ORDER_NOT_FOUND;

import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformErrorResponse;
import org.flab.deliveryplatform.order.application.port.exception.OrderNotFoundException;
import org.flab.deliveryplatform.order.domain.exception.InvalidOrderStatusException;
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OrderNotFoundException.class)
    public DeliveryPlatformErrorResponse<Object> handleOrderNotFoundException(OrderNotFoundException e) {
        return DeliveryPlatformErrorResponse.error(null, ORDER_NOT_FOUND.name(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidOrderStatusException.class)
    public DeliveryPlatformErrorResponse<Object> handleInvalidOrderStatusException(InvalidOrderStatusException e) {
        return DeliveryPlatformErrorResponse.error(null, INVALID_ORDER_STATUS.name(), e.getMessage());
    }
}
