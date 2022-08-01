package org.flab.deliveryplatform.owner.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.UUID;
import org.flab.deliveryplatform.owner.application.port.EncryptionManager;
import org.flab.deliveryplatform.owner.application.port.OwnerRepository;
import org.flab.deliveryplatform.owner.application.port.dto.SignUpOwnerCommand;
import org.flab.deliveryplatform.owner.application.port.dto.SignUpOwnerResult;
import org.flab.deliveryplatform.owner.application.port.exception.DuplicatedEmailException;
import org.flab.deliveryplatform.owner.domain.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SignUpOwnerServiceTest {

    @InjectMocks
    SignUpOwnerService signUpOwnerService;

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    EncryptionManager encryptionManager;

    SignUpOwnerCommand signUpOwnerCommand;

    Owner savedOwner;

    @BeforeEach
    void init() {
        signUpOwnerCommand = new SignUpOwnerCommand(
            UUID.randomUUID().toString().substring(0, 20),
            UUID.randomUUID().toString().substring(0, 20) + "@gmail.com", "a12345678",
            "010-1234-5678"
        );

        savedOwner = Owner.builder()
            .id(Long.MAX_VALUE)
            .nickname(signUpOwnerCommand.getNickname())
            .email(signUpOwnerCommand.getEmail())
            .password(signUpOwnerCommand.getPassword())
            .phoneNumber(signUpOwnerCommand.getPhoneNumber())
            .build();
    }

    @Test
    void signUpTest() {
        given(ownerRepository.save(any(Owner.class)))
            .willReturn(savedOwner);

        SignUpOwnerResult signUpOwnerResult = signUpOwnerService.signUp(signUpOwnerCommand);

        assertThat(signUpOwnerResult.getId()).isEqualTo(savedOwner.getId());
    }

    @Test
    void signUpDuplicatedEmailTest() {
        given(ownerRepository.existsByEmail(savedOwner.getEmail()))
            .willReturn(true);

        assertThatThrownBy(() -> signUpOwnerService.signUp(signUpOwnerCommand))
            .isInstanceOf(DuplicatedEmailException.class);
    }
}
