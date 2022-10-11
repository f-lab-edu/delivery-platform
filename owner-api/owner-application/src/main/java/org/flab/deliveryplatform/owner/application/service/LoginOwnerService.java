package org.flab.deliveryplatform.owner.application.service;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.auth.AuthorizationData;
import org.flab.deliveryplatform.common.auth.Token;
import org.flab.deliveryplatform.owner.application.port.AuthorizationRepository;
import org.flab.deliveryplatform.owner.application.port.EncryptionManager;
import org.flab.deliveryplatform.owner.application.port.LoginOwnerUseCase;
import org.flab.deliveryplatform.owner.application.port.OwnerRepository;
import org.flab.deliveryplatform.owner.application.port.TokenProvider;
import org.flab.deliveryplatform.owner.application.port.dto.LoginOwnerCommand;
import org.flab.deliveryplatform.owner.application.port.exception.InvalidOwnerInfoException;
import org.flab.deliveryplatform.owner.domain.Owner;
import org.flab.deliveryplatform.owner.domain.auth.Authorization;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginOwnerService implements LoginOwnerUseCase {

    private final OwnerRepository ownerRepository;

    private final AuthorizationRepository authorizationRepository;

    private final EncryptionManager encryptionManager;

    private final TokenProvider tokenProvider;

    private final OwnerAuthProperties authProperties;

    @Override
    public AuthorizationData login(LoginOwnerCommand loginOwnerCommand) {
        Owner owner = ownerRepository.findByEmail(loginOwnerCommand.getEmail())
            .orElseThrow(InvalidOwnerInfoException::new);

        checkPassword(loginOwnerCommand, owner);

        Token accessToken = tokenProvider.generateToken();

        Authorization savedAuthorization = authorizationRepository.save(
            Authorization.builder()
                .accessToken(accessToken.getToken())
                .tokenType(accessToken.getTokenType())
                .ownerId(owner.getId())
                .issueDate(LocalDateTime.now())
                .accessTokenExpiredTimeSecs(authProperties.getToken().getAccessTokenExpiredTimeSecs())
                .build()
        );

        return new AuthorizationData(
            savedAuthorization.getAccessToken(),
            savedAuthorization.getTokenType(),
            savedAuthorization.getOwnerId(),
            savedAuthorization.getIssueDate(),
            savedAuthorization.getAccessTokenExpiredTimeSecs()
        );
    }

    private void checkPassword(LoginOwnerCommand loginOwnerCommand, Owner owner) {
        if (!encryptionManager.isMatch(loginOwnerCommand.getPassword(), owner.getPassword())) {
            throw new InvalidOwnerInfoException();
        }
    }
}
