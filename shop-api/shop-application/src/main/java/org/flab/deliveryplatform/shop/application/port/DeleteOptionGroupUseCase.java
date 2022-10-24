package org.flab.deliveryplatform.shop.application.port;

public interface DeleteOptionGroupUseCase {

    void deleteOptionGroup(Long shopId, Long ownerId, Long menuId, Long optionGroupId);
}
