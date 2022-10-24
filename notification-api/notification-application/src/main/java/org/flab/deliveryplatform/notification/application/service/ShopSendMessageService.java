package org.flab.deliveryplatform.notification.application.service;

import static org.flab.deliveryplatform.notification.application.service.ShopWebsocketConst.SHOP_PREFIX;

import java.io.IOException;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.notification.application.port.ShopNotificationRepository;
import org.flab.deliveryplatform.notification.application.port.WebsocketSessionRepository;
import org.flab.deliveryplatform.notification.application.port.dto.ShopNotificationMessage;
import org.flab.deliveryplatform.notification.domain.ShopNotification;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Service
@RequiredArgsConstructor
public class ShopSendMessageService {

    private final WebsocketSessionRepository websocketSessionRepository;
    private final ShopNotificationRepository shopNotificationRepository;

    private final TaskExecutor notificationTaskExecutor;

    public void sendMessage(ShopNotificationMessage message) {
        ShopNotification notification = ShopNotification.builder()
            .shopId(message.getShopId())
            .payload(message.getPayload())
            .build();
        shopNotificationRepository.save(notification);

        Set<WebSocketSession> sessions = websocketSessionRepository.findById(
            SHOP_PREFIX + message.getShopId());

        // TODO send message using other messaging platform when websocket fail to send message;
        if (sessions.size() != 0) {
            for (WebSocketSession session : sessions) {
                notificationTaskExecutor.execute(() -> {
                    try {
                        session.sendMessage(new TextMessage(message.getPayload()));
                    } catch (IOException e) {

                    }
                });
            }
        }
    }
}
