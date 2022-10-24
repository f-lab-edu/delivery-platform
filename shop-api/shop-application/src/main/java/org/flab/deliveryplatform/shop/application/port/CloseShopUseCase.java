package org.flab.deliveryplatform.shop.application.port;

public interface CloseShopUseCase {

    void closeShop(Long shopId, Long ownerId);
}
