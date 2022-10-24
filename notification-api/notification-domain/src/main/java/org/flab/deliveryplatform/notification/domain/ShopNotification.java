package org.flab.deliveryplatform.notification.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ShopNotification {

    private Long id;

    private Long shopId;

    private String payload;

    @Builder
    public ShopNotification(Long id, Long shopId, String payload) {
        this.id = id;
        this.shopId = shopId;
        this.payload = payload;
    }
}
