package org.flab.deliveryplatform.shop.interfaces.web.exception;

import static org.flab.deliveryplatform.shop.interfaces.web.exception.ShopErrorCode.DUPLICATE_OPTION_GROUP_NAME;
import static org.flab.deliveryplatform.shop.interfaces.web.exception.ShopErrorCode.MENU_NOT_FOUND;
import static org.flab.deliveryplatform.shop.interfaces.web.exception.ShopErrorCode.OPTION_GROUP_NOT_FOUND;
import static org.flab.deliveryplatform.shop.interfaces.web.exception.ShopErrorCode.OPTION_NOT_FOUND;
import static org.flab.deliveryplatform.shop.interfaces.web.exception.ShopErrorCode.SHOP_NOT_FOUND;

import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformErrorResponse;
import org.flab.deliveryplatform.shop.application.port.exception.ShopNotFoundException;
import org.flab.deliveryplatform.shop.domain.exception.DuplicatedOptionGroupNameException;
import org.flab.deliveryplatform.shop.domain.exception.MenuNotFoundException;
import org.flab.deliveryplatform.shop.domain.exception.OptionGroupNotFoundException;
import org.flab.deliveryplatform.shop.domain.exception.OptionNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(value = Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ShopRestControllerExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ShopNotFoundException.class)
    public DeliveryPlatformErrorResponse<Object> handleShopNotFoundException(ShopNotFoundException e) {
        return DeliveryPlatformErrorResponse.error(null, SHOP_NOT_FOUND.name(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MenuNotFoundException.class)
    public DeliveryPlatformErrorResponse<Object> handleMenuNotFoundException(MenuNotFoundException e) {
        return DeliveryPlatformErrorResponse.error(null, MENU_NOT_FOUND.name(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OptionGroupNotFoundException.class)
    public DeliveryPlatformErrorResponse<Object> handleOptionGroupNotFoundException(OptionGroupNotFoundException e) {
        return DeliveryPlatformErrorResponse.error(null, OPTION_GROUP_NOT_FOUND.name(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicatedOptionGroupNameException.class)
    public DeliveryPlatformErrorResponse<Object> handleDuplicateOptionGroupNameException(
        DuplicatedOptionGroupNameException e) {
        return DeliveryPlatformErrorResponse.error(null, DUPLICATE_OPTION_GROUP_NAME.name(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OptionNotFoundException.class)
    public DeliveryPlatformErrorResponse<Object> handleOptionNotFoundException(OptionNotFoundException e) {
        return DeliveryPlatformErrorResponse.error(null, OPTION_NOT_FOUND.name(), e.getMessage());
    }
}
