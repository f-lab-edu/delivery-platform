package org.flab.deliveryplatform.member.infrastructure.persistence;

import org.springframework.data.repository.CrudRepository;

public interface RedisMemberAuthorizationRepository extends CrudRepository<RedisAuthorization, String> {

}
