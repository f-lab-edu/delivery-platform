package org.flab.deliveryplatform.member.infrastructure;

import org.flab.deliveryplatform.common.auth.AuthorizationUseCase;
import org.flab.deliveryplatform.member.application.port.AuthorizationRepository;
import org.flab.deliveryplatform.member.application.service.AuthorizationService;
import org.flab.deliveryplatform.member.infrastructure.persistence.AuthorizationRepositoryAdapter;
import org.flab.deliveryplatform.member.infrastructure.persistence.MemoryMemberAuthorizationRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemberAuthorizationConfiguration {

    @Bean
    public AuthorizationUseCase memberAuthorizationService(AuthorizationRepository authorizationRepository) {
        return new AuthorizationService(authorizationRepository);
    }
    
    @Bean
    public AuthorizationRepository memberAuthorizationRepository(
        MemoryMemberAuthorizationRepository authorizationRepository) {
        return new AuthorizationRepositoryAdapter(authorizationRepository);
    }
}
