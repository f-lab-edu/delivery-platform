package org.flab.deliveryplatform.shop.application.port;

import org.flab.deliveryplatform.shop.application.port.dto.ShopData;
import org.flab.deliveryplatform.shop.application.port.exception.ShopNotFoundException;

public interface GetShopUseCase {

    ShopData getShop(Long shopId) throws ShopNotFoundException;
}
