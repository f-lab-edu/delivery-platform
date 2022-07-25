package org.flab.deliveryplatform.owner.interfaces.web.exception;

import static org.flab.deliveryplatform.owner.interfaces.web.exception.OwnerErrorCode.OWNER_DUPLICATED_EMAIL;

import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformErrorResponse;
import org.flab.deliveryplatform.owner.application.port.exception.DuplicatedEmailException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OwnerRestControllerExceptionAdvice {

    @ExceptionHandler(DuplicatedEmailException.class)
    public ResponseEntity<DeliveryPlatformErrorResponse<Object>> duplicatedEmailExHandler(
        DuplicatedEmailException ex) {
        return ResponseEntity.badRequest().body(
            DeliveryPlatformErrorResponse.error(null, OWNER_DUPLICATED_EMAIL.name(),
                ex.getMessage()));
    }
}
