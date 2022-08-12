package org.flab.deliveryplatform.owner.application.port;

import java.util.Optional;
import org.flab.deliveryplatform.owner.domain.auth.Authorization;

public interface AuthorizationRepository {

    Authorization save(Authorization authorization);

    Optional<Authorization> findById(String id);
}
