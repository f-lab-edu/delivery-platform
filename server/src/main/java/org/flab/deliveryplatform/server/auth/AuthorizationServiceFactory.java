package org.flab.deliveryplatform.server.auth;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.auth.AuthorizationUseCase;
import org.flab.deliveryplatform.common.auth.UserType;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorizationServiceFactory {

    private final Map<UserType, AuthorizationUseCase> authorizationServiceMap;

    public AuthorizationUseCase getAuthorizationUseCase(UserType userType) {
        return authorizationServiceMap.get(userType);
    }
}
