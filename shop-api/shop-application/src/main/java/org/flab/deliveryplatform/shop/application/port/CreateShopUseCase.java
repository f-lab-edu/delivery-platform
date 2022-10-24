package org.flab.deliveryplatform.shop.application.port;

import org.flab.deliveryplatform.shop.application.port.dto.CreateShopCommand;

public interface CreateShopUseCase {

    void createShop(Long ownerId, CreateShopCommand command);
}
