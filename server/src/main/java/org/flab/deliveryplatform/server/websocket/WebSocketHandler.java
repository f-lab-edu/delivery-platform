package org.flab.deliveryplatform.server.websocket;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.notification.application.service.ShopWebSocketSessionService;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {

    private final ShopWebSocketSessionService webSocketSessionService;

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String path = session.getUri().getPath();
        if (!antPathMatcher.match("/ws/owners/shops", path)) {
            return;
        }
        Long shopId = parseShopId(session.getUri().getQuery());
        webSocketSessionService.save(String.valueOf(shopId), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String path = session.getUri().getPath();
        if (!antPathMatcher.match("/ws/owners/shops", path)) {
            return;
        }
        Long shopId = parseShopId(session.getUri().getQuery());
        webSocketSessionService.delete(String.valueOf(shopId), session);
    }

    private Long parseShopId(String queryString) {
        String[] queries = queryString.split("&");
        for (String query : queries) {
            String[] split = query.split("=");
            if (split[0].equals("id")) {
                return Long.parseLong(split[1]);
            }
        }
        throw new RuntimeException();
    }

}
