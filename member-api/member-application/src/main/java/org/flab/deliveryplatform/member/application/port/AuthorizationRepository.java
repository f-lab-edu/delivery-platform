package org.flab.deliveryplatform.member.application.port;

import java.util.Optional;
import org.flab.deliveryplatform.member.domain.authorization.Authorization;
import org.flab.deliveryplatform.member.domain.authorization.AuthorizationId;

public interface AuthorizationRepository {

    Authorization save(Authorization authorization);

    Optional<Authorization> findById(AuthorizationId id);
}
