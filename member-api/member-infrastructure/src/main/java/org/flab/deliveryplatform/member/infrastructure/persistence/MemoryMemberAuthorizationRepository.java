package org.flab.deliveryplatform.member.infrastructure.persistence;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.flab.deliveryplatform.member.domain.authorization.Authorization;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryMemberAuthorizationRepository {

    private static final Map<String, Authorization> store = new ConcurrentHashMap<>();

    public Authorization save(Authorization authorization) {
        store.put(authorization.getAccessToken(), authorization);
        return authorization;
    }

    public Optional<Authorization> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }
}
