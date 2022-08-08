package org.flab.deliveryplatform.owner.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import org.flab.deliveryplatform.common.auth.AuthorizationData;
import org.flab.deliveryplatform.owner.application.port.AuthorizationRepository;
import org.flab.deliveryplatform.owner.application.port.EncryptionManager;
import org.flab.deliveryplatform.owner.application.port.OwnerRepository;
import org.flab.deliveryplatform.owner.application.port.TokenProvider;
import org.flab.deliveryplatform.owner.application.port.dto.LoginOwnerCommand;
import org.flab.deliveryplatform.owner.application.port.exception.InvalidOwnerInfoException;
import org.flab.deliveryplatform.owner.domain.Owner;
import org.flab.deliveryplatform.owner.domain.auth.Authorization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LoginOwnerServiceTest {

    @InjectMocks
    LoginOwnerService loginOwnerService;

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    AuthorizationRepository authorizationRepository;

    @Mock
    EncryptionManager encryptionManager;

    @Mock
    TokenProvider tokenProvider;

    Owner owner;

    Authorization authorization;

    LoginOwnerCommand loginOwnerCommand;

    LoginOwnerCommand invalidPasswordOwnerCommand;

    @BeforeEach
    void beforeEach() {
        owner = Owner.builder()
            .id(0L)
            .nickname("nickname")
            .email("nickname@gmail.com")
            .password("12345678")
            .phoneNumber("010-1234-5678")
            .build();

        authorization = Authorization.builder()
            .accessToken(UUID.randomUUID().toString())
            .ownerId(owner.getId())
            .issueDate(LocalDateTime.now())
            .build();

        loginOwnerCommand = new LoginOwnerCommand(owner.getEmail(), owner.getPassword());

        invalidPasswordOwnerCommand = new LoginOwnerCommand(owner.getEmail(), owner.getPassword() + "1");
    }

    @Test
    void loginTest() {
        given(ownerRepository.findByEmail(loginOwnerCommand.getEmail()))
            .willReturn(Optional.ofNullable(owner));

        given(encryptionManager.isMatch(loginOwnerCommand.getPassword(), owner.getPassword()))
            .willReturn(true);

        given(authorizationRepository.save(any(Authorization.class)))
            .willReturn(authorization);

        AuthorizationData authorizationData = loginOwnerService.login(loginOwnerCommand);

        assertThat(authorizationData.getAccessToken())
            .isEqualTo(authorization.getAccessToken());
        assertThat(authorizationData.getUserId())
            .isEqualTo(authorization.getOwnerId());

    }

    @Test
    void invalidEmailTest() {
        given(ownerRepository.findByEmail(loginOwnerCommand.getEmail()))
            .willReturn(Optional.empty());

        assertThatThrownBy(() -> loginOwnerService.login(loginOwnerCommand))
            .isInstanceOf(InvalidOwnerInfoException.class);
    }

    @Test
    void invalidPasswordTest() {
        given(ownerRepository.findByEmail(invalidPasswordOwnerCommand.getEmail()))
            .willReturn(Optional.ofNullable(owner));

        assertThatThrownBy(() -> loginOwnerService.login(invalidPasswordOwnerCommand))
            .isInstanceOf(InvalidOwnerInfoException.class);
    }
}
