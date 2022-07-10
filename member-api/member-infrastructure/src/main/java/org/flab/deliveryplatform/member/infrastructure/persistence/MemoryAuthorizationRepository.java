package org.flab.deliveryplatform.member.infrastructure.persistence;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.flab.deliveryplatform.member.domain.authorization.Authorization;
import org.flab.deliveryplatform.member.domain.authorization.AuthorizationId;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryAuthorizationRepository {

    private static final Map<AuthorizationId, Authorization> AUTHORIZATION_STORE = new ConcurrentHashMap();

    public Authorization save(Authorization authorization) {
        AUTHORIZATION_STORE.put(authorization.getAuthorizationId(), authorization);
        return authorization;
    }

    public Optional<Authorization> findByKey(AuthorizationId key) {
        return Optional.ofNullable(AUTHORIZATION_STORE.get(key));
    }
}
