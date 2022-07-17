package org.flab.deliveryplatform.shop.interfaces.web.exception;

import static org.flab.deliveryplatform.shop.interfaces.web.exception.ShopErrorCode.SHOP_NOT_FOUND;

import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformErrorResponse;
import org.flab.deliveryplatform.shop.application.port.exception.ShopNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(value = Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ShopRestControllerExceptionAdvice {

    @ExceptionHandler(ShopNotFoundException.class)
    public ResponseEntity<DeliveryPlatformErrorResponse<Object>> handleShopNotFoundException(ShopNotFoundException e) {
        System.out.println(111);
        return ResponseEntity.badRequest().body(
            DeliveryPlatformErrorResponse.error(null, SHOP_NOT_FOUND.name(), e.getMessage()));
    }
}
