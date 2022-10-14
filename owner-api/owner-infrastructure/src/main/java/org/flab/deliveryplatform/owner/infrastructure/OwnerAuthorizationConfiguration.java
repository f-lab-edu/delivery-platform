package org.flab.deliveryplatform.owner.infrastructure;

import org.flab.deliveryplatform.common.auth.AuthorizationUseCase;
import org.flab.deliveryplatform.owner.application.port.AuthorizationRepository;
import org.flab.deliveryplatform.owner.application.service.AuthorizationService;
import org.flab.deliveryplatform.owner.infrastructure.persistance.AuthorizationRepositoryAdapter;
import org.flab.deliveryplatform.owner.infrastructure.persistance.RedisOwnerAuthorizationRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OwnerAuthorizationConfiguration {

    @Bean
    public AuthorizationUseCase ownerAuthorizationService(AuthorizationRepository authorizationRepository) {
        return new AuthorizationService(authorizationRepository);
    }

    @Bean
    public AuthorizationRepository ownerAuthorizationRepositoryAdapter(
        RedisOwnerAuthorizationRepository authorizationRepository) {
        return new AuthorizationRepositoryAdapter(authorizationRepository);
    }

}
