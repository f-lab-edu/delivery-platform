package org.flab.deliveryplatform.shop.application.port;

import org.flab.deliveryplatform.shop.application.port.dto.CreateMenuCommand;

public interface CreateMenuUseCase {

    void createMenu(Long shopId, Long ownerId, CreateMenuCommand command);
}
