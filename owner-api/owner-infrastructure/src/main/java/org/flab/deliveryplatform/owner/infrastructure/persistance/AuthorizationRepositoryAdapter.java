package org.flab.deliveryplatform.owner.infrastructure.persistance;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.owner.application.port.AuthorizationRepository;
import org.flab.deliveryplatform.owner.domain.auth.Authorization;

@RequiredArgsConstructor
public class AuthorizationRepositoryAdapter implements AuthorizationRepository {

    private final RedisOwnerAuthorizationRepository authorizationRepository;

    @Override
    public Authorization save(Authorization authorization) {
        RedisAuthorization redisAuthorization = OwnerAuthorizationMapper.from(authorization);
        authorizationRepository.save(redisAuthorization);
        return authorization;
    }

    @Override
    public Optional<Authorization> findById(String id) {
        return authorizationRepository.findById(id)
            .map(OwnerAuthorizationMapper::from);
    }
}
