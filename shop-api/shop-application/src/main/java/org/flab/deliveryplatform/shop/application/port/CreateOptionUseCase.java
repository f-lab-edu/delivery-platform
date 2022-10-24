package org.flab.deliveryplatform.shop.application.port;

import org.flab.deliveryplatform.shop.application.port.dto.OptionCommand;

public interface CreateOptionUseCase {

    void createOption(Long shopId, Long ownerId, Long menuId, Long optionGroupId, OptionCommand command);
}
