package org.flab.deliveryplatform.owner.interfaces.web.exception;

import static org.flab.deliveryplatform.owner.interfaces.web.exception.OwnerErrorCode.OWNER_DUPLICATED_EMAIL;

import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformErrorResponse;
import org.flab.deliveryplatform.owner.application.port.exception.DuplicatedEmailException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class OwnerRestControllerExceptionAdvice {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicatedEmailException.class)
    public DeliveryPlatformErrorResponse<Object> duplicatedEmailExHandler(DuplicatedEmailException ex) {
        return DeliveryPlatformErrorResponse.error(null, OWNER_DUPLICATED_EMAIL.name(), ex.getMessage());
    }
}
