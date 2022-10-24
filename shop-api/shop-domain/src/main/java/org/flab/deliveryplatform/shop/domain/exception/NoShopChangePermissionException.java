package org.flab.deliveryplatform.shop.domain.exception;

public class NoShopChangePermissionException extends RuntimeException {

    public NoShopChangePermissionException() {
        super("User has no change permission to this shop");
    }

    public NoShopChangePermissionException(Long shopId, Long ownerId) {
        super(
            String.format("User (ownerId = %s) has no change permission to this shop (shopId = %s)", ownerId, shopId));
    }
}
