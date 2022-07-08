package org.flab.deliveryplatform.member.infrastructure.persistence;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.member.application.port.AuthorizationRepository;
import org.flab.deliveryplatform.member.domain.authorization.Authorization;
import org.flab.deliveryplatform.member.domain.authorization.AuthorizationId;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class TokenRepositoryAdapter implements AuthorizationRepository {

    private final MemoryAuthorizationRepository memoryAuthorizationRepository;

    public Authorization save(Authorization authorization) {
        return memoryAuthorizationRepository.save(authorization);
    }

    public Optional<Authorization> findById(AuthorizationId id) {
        return memoryAuthorizationRepository.findByKey(id);
    }
}
