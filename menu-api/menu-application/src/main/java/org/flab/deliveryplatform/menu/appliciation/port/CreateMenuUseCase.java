package org.flab.deliveryplatform.menu.appliciation.port;

import org.flab.deliveryplatform.menu.appliciation.port.dto.CreateMenuCommand;

public interface CreateMenuUseCase {

    void createMenu(Long shopId, CreateMenuCommand command);
}
