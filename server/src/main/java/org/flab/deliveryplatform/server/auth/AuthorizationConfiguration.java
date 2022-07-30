package org.flab.deliveryplatform.server.auth;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.flab.deliveryplatform.common.auth.AuthorizationUseCase;
import org.flab.deliveryplatform.common.auth.UserType;
import org.flab.deliveryplatform.owner.application.service.AuthorizationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthorizationConfiguration {

    @Bean
    public Map<UserType, AuthorizationUseCase> authServiceMap(List<AuthorizationUseCase> serviceList) {
        Map<UserType, AuthorizationUseCase> authServiceMap = new HashMap<>();
        for (AuthorizationUseCase authService : serviceList) {
            if (authService instanceof AuthorizationService) {
                authServiceMap.put(UserType.OWNER, authService);
            }
        }
        return authServiceMap;
    }

}
