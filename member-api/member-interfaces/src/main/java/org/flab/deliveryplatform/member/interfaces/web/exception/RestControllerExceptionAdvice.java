package org.flab.deliveryplatform.member.interfaces.web.exception;

import static org.flab.deliveryplatform.member.interfaces.web.exception.MemberErrorCode.M_DUPLICATED_EMAIL;
import static org.flab.deliveryplatform.member.interfaces.web.exception.MemberErrorCode.M_INVALID_MEMBER_INFO;

import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformErrorResponse;
import org.flab.deliveryplatform.member.application.port.exception.DuplicatedEmailException;
import org.flab.deliveryplatform.member.application.port.exception.InvalidMemberInfoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerExceptionAdvice {

    @ExceptionHandler(DuplicatedEmailException.class)
    public ResponseEntity<DeliveryPlatformErrorResponse<Object>> duplicatedEmailExHandler(
        DuplicatedEmailException ex) {
        return ResponseEntity.badRequest().body(
            DeliveryPlatformErrorResponse.error(null, M_DUPLICATED_EMAIL.name(),
                ex.getMessage()));
    }

    @ExceptionHandler(InvalidMemberInfoException.class)
    public ResponseEntity<DeliveryPlatformErrorResponse<Object>> invalidMemberInfoExHandler(
        InvalidMemberInfoException ex) {
        return ResponseEntity.badRequest().body(
            DeliveryPlatformErrorResponse.error(null, M_INVALID_MEMBER_INFO.name(),
                ex.getMessage()));
    }
}
