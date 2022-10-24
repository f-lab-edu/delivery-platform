package org.flab.deliveryplatform.notification.infrastructure.persistence;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.notification.application.port.WebsocketSessionRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
@RequiredArgsConstructor
public class WebsocketSessionRepositoryAdapter implements WebsocketSessionRepository {

    private final MemoryWebSocketSessionRepository webSocketSessionRepository;

    @Override
    public void save(String userId, WebSocketSession webSocketSession) {
        webSocketSessionRepository.save(userId, webSocketSession);
    }

    @Override
    public Set<WebSocketSession> findById(String userId) {
        return webSocketSessionRepository.findById(userId);
    }

    @Override
    public void delete(String userId, WebSocketSession webSocketSession) {
        webSocketSessionRepository.delete(userId, webSocketSession);
    }

    @Override
    public void deleteSets(String id, Set<WebSocketSession> webSocketSessions) {
        webSocketSessionRepository.deleteSets(id, webSocketSessions);
    }
}
