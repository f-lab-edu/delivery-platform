package org.flab.deliveryplatform.member.application.port;

import java.util.Optional;
import org.flab.deliveryplatform.member.domain.token.Authorization;
import org.flab.deliveryplatform.member.domain.token.AuthorizationKey;
import org.flab.deliveryplatform.member.domain.token.AuthorizationValue;

public interface AuthorizationRepository {

    Authorization save(Authorization authorization);

    Optional<AuthorizationValue> findValueByKey(AuthorizationKey key);
}
