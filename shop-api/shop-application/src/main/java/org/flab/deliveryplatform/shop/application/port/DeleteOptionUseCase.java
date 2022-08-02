package org.flab.deliveryplatform.shop.application.port;

public interface DeleteOptionUseCase {

    void deleteOption(Long shopId, Long menuId, Long optionGroupId, Long optionId);
}
