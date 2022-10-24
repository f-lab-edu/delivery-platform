package org.flab.deliveryplatform.notification.infrastructure.persistence;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.notification.application.port.ShopNotificationRepository;
import org.flab.deliveryplatform.notification.domain.ShopNotification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShopNotificationRepositoryAdapter implements ShopNotificationRepository {

    private final MemoryShopNotificationRepository shopNotificationRepository;

    @Override
    public ShopNotification save(ShopNotification shopNotification) {
        return shopNotificationRepository.save(shopNotification);
    }
}
