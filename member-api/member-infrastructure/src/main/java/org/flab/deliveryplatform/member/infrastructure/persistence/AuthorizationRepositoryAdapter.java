package org.flab.deliveryplatform.member.infrastructure.persistence;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.member.application.port.AuthorizationRepository;
import org.flab.deliveryplatform.member.domain.authorization.Authorization;

@RequiredArgsConstructor
public class AuthorizationRepositoryAdapter implements AuthorizationRepository {

    private final MemoryMemberAuthorizationRepository authorizationRepository;

    public Authorization save(Authorization authorization) {
        return authorizationRepository.save(authorization);
    }

    public Optional<Authorization> findById(String id) {
        return authorizationRepository.findById(id);
    }
}
