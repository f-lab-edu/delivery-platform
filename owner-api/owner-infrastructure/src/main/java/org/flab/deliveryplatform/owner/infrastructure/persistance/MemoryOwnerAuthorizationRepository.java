package org.flab.deliveryplatform.owner.infrastructure.persistance;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.flab.deliveryplatform.owner.domain.auth.Authorization;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryOwnerAuthorizationRepository {

    private static final Map<String, Authorization> STORE = new ConcurrentHashMap<>();

    public Authorization save(Authorization authorization) {
        STORE.put(authorization.getAccessToken(), authorization);
        return authorization;
    }

    public Optional<Authorization> findById(String id) {
        return Optional.ofNullable(STORE.get(id));
    }

}
