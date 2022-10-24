package org.flab.deliveryplatform.notification.application.port.dto;

import lombok.Getter;

@Getter
public class ShopNotificationMessage {

    private Long shopId;

    private String payload;

    public ShopNotificationMessage(Long shopId, String payload) {
        this.shopId = shopId;
        this.payload = payload;
    }
}
