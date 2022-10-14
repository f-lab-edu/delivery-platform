package org.flab.deliveryplatform.member.infrastructure.persistence;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.member.application.port.AuthorizationRepository;
import org.flab.deliveryplatform.member.domain.authorization.Authorization;

@RequiredArgsConstructor
public class AuthorizationRepositoryAdapter implements AuthorizationRepository {

    private final RedisMemberAuthorizationRepository redisRepository;

    public Authorization save(Authorization authorization) {
        RedisAuthorization redisAuthorization = MemberAuthorizationMapper.from(authorization);
        redisRepository.save(redisAuthorization);
        return authorization;
    }

    public Optional<Authorization> findById(String id) {
        Optional<RedisAuthorization> redisAuthorization = redisRepository.findById(id);
        return redisAuthorization.map(MemberAuthorizationMapper::from);
    }
}
