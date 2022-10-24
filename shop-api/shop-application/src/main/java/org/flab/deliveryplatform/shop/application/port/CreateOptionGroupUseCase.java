package org.flab.deliveryplatform.shop.application.port;

import org.flab.deliveryplatform.shop.application.port.dto.CreateOptionGroupCommand;

public interface CreateOptionGroupUseCase {

    void createOptionGroup(Long shopId, Long ownerId, Long menuId, CreateOptionGroupCommand command);
}
