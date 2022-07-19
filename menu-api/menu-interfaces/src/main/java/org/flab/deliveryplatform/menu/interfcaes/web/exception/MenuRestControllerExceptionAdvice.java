package org.flab.deliveryplatform.menu.interfcaes.web.exception;

import static org.flab.deliveryplatform.menu.interfcaes.web.exception.MenuErrorCode.SHOP_NOT_FOUND;

import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformErrorResponse;
import org.flab.deliveryplatform.menu.appliciation.port.exception.ShopNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(value = Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class MenuRestControllerExceptionAdvice {

    @ExceptionHandler(ShopNotFoundException.class)
    public ResponseEntity<DeliveryPlatformErrorResponse<Object>> handleShopNotFoundException(ShopNotFoundException e) {
        return ResponseEntity.badRequest().body(
            DeliveryPlatformErrorResponse.error(null, SHOP_NOT_FOUND.name(), e.getMessage()));
    }
}
