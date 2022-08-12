package org.flab.deliveryplatform.shop.application.port;

import org.flab.deliveryplatform.shop.application.port.dto.ShopDetailData;
import org.flab.deliveryplatform.shop.application.port.exception.ShopNotFoundException;

public interface GetShopUseCase {

    ShopDetailData getShop(Long shopId) throws ShopNotFoundException;
}
