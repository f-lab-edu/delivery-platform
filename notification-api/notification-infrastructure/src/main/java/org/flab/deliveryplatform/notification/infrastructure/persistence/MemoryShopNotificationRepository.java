package org.flab.deliveryplatform.notification.infrastructure.persistence;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.flab.deliveryplatform.notification.domain.ShopNotification;
import org.springframework.stereotype.Component;

@Component
public class MemoryShopNotificationRepository {

    private static final Map<Long, ShopNotification> STORE = new ConcurrentHashMap<>();
    private static final AtomicLong SEQUENCE = new AtomicLong();

    public ShopNotification save(ShopNotification shopNotification) {
        ShopNotification savedShopNotification = ShopNotification.builder()
            .id(SEQUENCE.incrementAndGet())
            .shopId(shopNotification.getShopId())
            .payload(shopNotification.getPayload())
            .build();
        STORE.put(savedShopNotification.getId(), savedShopNotification);
        return savedShopNotification;
    }
}
