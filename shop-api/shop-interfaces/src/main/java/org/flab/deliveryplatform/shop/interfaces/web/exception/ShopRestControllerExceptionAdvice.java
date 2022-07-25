package org.flab.deliveryplatform.shop.interfaces.web.exception;

import static org.flab.deliveryplatform.shop.interfaces.web.exception.ShopErrorCode.DUPLICATE_OPTION_GROUP_NAME;
import static org.flab.deliveryplatform.shop.interfaces.web.exception.ShopErrorCode.MENU_NOT_FOUND;
import static org.flab.deliveryplatform.shop.interfaces.web.exception.ShopErrorCode.OPTION_GROUP_NOT_FOUND;
import static org.flab.deliveryplatform.shop.interfaces.web.exception.ShopErrorCode.SHOP_NOT_FOUND;

import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformErrorResponse;
import org.flab.deliveryplatform.shop.application.port.exception.ShopNotFoundException;
import org.flab.deliveryplatform.shop.domain.exception.DuplicateOptionGroupNameException;
import org.flab.deliveryplatform.shop.domain.exception.MenuNotFoundException;
import org.flab.deliveryplatform.shop.domain.exception.OptionGroupNotFoundException;
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
        return ResponseEntity.badRequest().body(
            DeliveryPlatformErrorResponse.error(null, SHOP_NOT_FOUND.name(), e.getMessage()));
    }

    @ExceptionHandler(MenuNotFoundException.class)
    public ResponseEntity<DeliveryPlatformErrorResponse<Object>> handleMenuNotFoundException(MenuNotFoundException e) {
        return ResponseEntity.badRequest().body(
            DeliveryPlatformErrorResponse.error(null, MENU_NOT_FOUND.name(), e.getMessage()));
    }

    @ExceptionHandler(OptionGroupNotFoundException.class)
    public ResponseEntity<DeliveryPlatformErrorResponse<Object>> handleOptionGroupNotFoundException(
        OptionGroupNotFoundException e) {
        return ResponseEntity.badRequest().body(
            DeliveryPlatformErrorResponse.error(null, OPTION_GROUP_NOT_FOUND.name(), e.getMessage()));
    }

    @ExceptionHandler(DuplicateOptionGroupNameException.class)
    public ResponseEntity<DeliveryPlatformErrorResponse<Object>> handleDuplicateOptionGroupNameException(
        DuplicateOptionGroupNameException e) {
        return ResponseEntity.badRequest().body(
            DeliveryPlatformErrorResponse.error(null, DUPLICATE_OPTION_GROUP_NAME.name(), e.getMessage()));
    }
}
