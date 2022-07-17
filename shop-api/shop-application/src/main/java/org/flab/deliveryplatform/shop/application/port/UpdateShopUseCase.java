package org.flab.deliveryplatform.shop.application.port;

import org.flab.deliveryplatform.shop.application.port.dto.UpdateShopCommand;
import org.flab.deliveryplatform.shop.application.port.exception.ShopNotFoundException;

public interface UpdateShopUseCase {

    void updateShop(Long shopId, UpdateShopCommand command) throws ShopNotFoundException;
}
