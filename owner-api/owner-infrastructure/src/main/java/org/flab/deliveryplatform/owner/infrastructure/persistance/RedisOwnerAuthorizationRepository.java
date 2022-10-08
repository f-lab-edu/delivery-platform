package org.flab.deliveryplatform.owner.infrastructure.persistance;

import org.springframework.data.repository.CrudRepository;

public interface RedisOwnerAuthorizationRepository extends CrudRepository<RedisAuthorization, String> {

}
