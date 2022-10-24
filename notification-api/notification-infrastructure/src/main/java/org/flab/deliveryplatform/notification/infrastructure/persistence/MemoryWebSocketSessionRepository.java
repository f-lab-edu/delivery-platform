package org.flab.deliveryplatform.notification.infrastructure.persistence;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
public class MemoryWebSocketSessionRepository {

    private static final ConcurrentHashMap<String, Set<WebSocketSession>> STORE = new ConcurrentHashMap<>();

    public void save(String id, WebSocketSession webSocketSession) {
        Set<WebSocketSession> sessions;
        if (!STORE.containsKey(id)) {
            sessions = new HashSet<>();
            STORE.put(id, sessions);
        } else {
            sessions = STORE.get(id);
        }
        sessions.add(webSocketSession);
    }

    public void delete(String id, WebSocketSession webSocketSession) {
        if (!STORE.containsKey(id)) {
            return;
        }
        Set<WebSocketSession> sessions = STORE.get(id);
        sessions.remove(webSocketSession);
        if (sessions.size() == 0) {
            STORE.remove(id);
        }
    }

    public Set<WebSocketSession> findById(String id) {
        if (STORE.get(id) == null) {
            return new HashSet<>();
        }
        return Collections.unmodifiableSet(STORE.get(id));
    }

    public void deleteSets(String id, Set<WebSocketSession> webSocketSessions) {
        if (!STORE.containsKey(id)) {
            return;
        }
        Set<WebSocketSession> sessions = STORE.get(id);
        sessions.removeAll(webSocketSessions);
        if (sessions.size() == 0) {
            STORE.remove(id);
        }
    }
}
