package org.flab.deliveryplatform.owner.application.service;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.owner.application.port.EncryptionManager;
import org.flab.deliveryplatform.owner.application.port.OwnerRepository;
import org.flab.deliveryplatform.owner.application.port.SignUpOwnerUseCase;
import org.flab.deliveryplatform.owner.application.port.dto.SignUpOwnerCommand;
import org.flab.deliveryplatform.owner.application.port.dto.SignUpOwnerResult;
import org.flab.deliveryplatform.owner.application.port.exception.DuplicatedEmailException;
import org.flab.deliveryplatform.owner.domain.Owner;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpOwnerService implements SignUpOwnerUseCase {

    private final OwnerRepository ownerRepository;
    private final EncryptionManager encryptionManager;

    public SignUpOwnerResult signUp(SignUpOwnerCommand signUpOwnerCommand) {
        validateSignUp(signUpOwnerCommand);
        Owner savedOwner = save(signUpOwnerCommand);
        return new SignUpOwnerResult(savedOwner.getId());
    }

    private void validateSignUp(SignUpOwnerCommand signUpOwnerCommand) {
        if (ownerRepository.existsByEmail(signUpOwnerCommand.getEmail())) {
            throw new DuplicatedEmailException();
        }
    }

    private Owner save(SignUpOwnerCommand signUpOwnerCommand) {
        Owner owner = Owner.builder()
            .id(0L)
            .nickname(signUpOwnerCommand.getNickname())
            .email(signUpOwnerCommand.getEmail())
            .password(encryptionManager.encrypt(signUpOwnerCommand.getPassword()))
            .phoneNumber(signUpOwnerCommand.getPhoneNumber())
            .build();
        return ownerRepository.save(owner);
    }
}
