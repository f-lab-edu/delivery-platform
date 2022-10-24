package org.flab.deliveryplatform.notification.application.port;

import org.flab.deliveryplatform.notification.domain.ShopNotification;

public interface ShopNotificationRepository {

    ShopNotification save(ShopNotification shopNotification);
}
