package org.flab.deliveryplatform.notification.application.port;

import java.util.Set;
import org.springframework.web.socket.WebSocketSession;

public interface WebsocketSessionRepository {

    void save(String id, WebSocketSession webSocketSession);

    Set<WebSocketSession> findById(String id);

    void delete(String id, WebSocketSession webSocketSession);

    void deleteSets(String id, Set<WebSocketSession> sessions);
}
