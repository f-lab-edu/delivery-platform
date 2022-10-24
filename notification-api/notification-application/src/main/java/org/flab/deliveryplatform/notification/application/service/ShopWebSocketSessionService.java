package org.flab.deliveryplatform.notification.application.service;

import static org.flab.deliveryplatform.notification.application.service.ShopWebsocketConst.SHOP_PREFIX;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.notification.application.port.WebsocketSessionRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
@RequiredArgsConstructor
public class ShopWebSocketSessionService {

    private final WebsocketSessionRepository websocketSessionRepository;

    public void save(String shopId, WebSocketSession webSocketSession) {
        websocketSessionRepository.save(SHOP_PREFIX + shopId, webSocketSession);
    }

    public void delete(String shopId, WebSocketSession webSocketSession) {
        websocketSessionRepository.delete(SHOP_PREFIX + shopId, webSocketSession);
    }
}
