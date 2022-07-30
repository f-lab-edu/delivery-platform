package org.flab.deliveryplatform.member.infrastructure.persistence;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.member.application.port.AuthorizationRepository;
import org.flab.deliveryplatform.member.domain.authorization.Authorization;

@RequiredArgsConstructor
public class AuthorizationRepositoryAdapter implements AuthorizationRepository {

    private final MemoryMemberAuthorizationRepository memoryAuthorizationRepository;

    public Authorization save(Authorization authorization) {
        return memoryAuthorizationRepository.save(authorization);
    }

    public Optional<Authorization> findById(String id) {
        return memoryAuthorizationRepository.findById(id);
    }
}
