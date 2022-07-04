package org.flab.deliveryplatform.member.infrastructure.persistence;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.flab.deliveryplatform.member.domain.token.Authorization;
import org.flab.deliveryplatform.member.domain.token.AuthorizationKey;
import org.flab.deliveryplatform.member.domain.token.AuthorizationValue;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryAuthorizationRepository {

    private static final Map<AuthorizationKey, AuthorizationValue> AUTHORIZATION_STORE = new ConcurrentHashMap();

    public Authorization save(Authorization authorization) {
        AUTHORIZATION_STORE.put(authorization.getAuthorizationKey(), authorization.getAuthorizationValue());
        return authorization;
    }

    public Optional<AuthorizationValue> findByKey(AuthorizationKey key) {
        return Optional.ofNullable(AUTHORIZATION_STORE.get(key));
    }
}
