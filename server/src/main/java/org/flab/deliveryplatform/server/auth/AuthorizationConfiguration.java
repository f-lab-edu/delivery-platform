package org.flab.deliveryplatform.server.auth;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.flab.deliveryplatform.common.auth.AuthorizationUseCase;
import org.flab.deliveryplatform.common.auth.UserType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthorizationConfiguration {

    @Bean
    public Map<UserType, AuthorizationUseCase> authServiceMap(List<AuthorizationUseCase> serviceList) {
        return serviceList.stream()
            .filter(authUseCase -> authUseCase.getUserType() != null)
            .collect(Collectors.toMap(AuthorizationUseCase::getUserType, Function.identity()));
    }

}
