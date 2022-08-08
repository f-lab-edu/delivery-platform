package org.flab.deliveryplatform.owner.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import org.flab.deliveryplatform.common.auth.AuthorizationData;
import org.flab.deliveryplatform.common.auth.exception.InvalidAuthorizationException;
import org.flab.deliveryplatform.owner.application.port.AuthorizationRepository;
import org.flab.deliveryplatform.owner.domain.auth.Authorization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AuthorizationServiceTest {

    @InjectMocks
    AuthorizationService authorizationService;

    @Mock
    AuthorizationRepository authorizationRepository;

    String accessToken;

    String invalidAccessToken;

    Authorization authorization;


    @BeforeEach
    void beforeEach() {
        accessToken = UUID.randomUUID().toString();

        authorization = Authorization.builder()
            .accessToken(accessToken)
            .ownerId(0L)
            .issueDate(LocalDateTime.now())
            .build();

        invalidAccessToken = UUID.randomUUID().toString();
    }

    @Test
    void getAuthorizationTest() {
        given(authorizationRepository.findById(accessToken))
            .willReturn(Optional.ofNullable(authorization));

        AuthorizationData authorizationData = authorizationService.getAuthorizationData(accessToken);

        assertThat(authorizationData.getAccessToken())
            .isEqualTo(accessToken);

        assertThat(authorizationData.getUserId())
            .isEqualTo(authorization.getOwnerId());
    }

    @Test
    void invalidAccessTokenTest() {
        assertThatThrownBy(() -> authorizationService.getAuthorizationData(invalidAccessToken))
            .isInstanceOf(InvalidAuthorizationException.class);
    }

}
