package org.flab.deliveryplatform.owner.application.service;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.auth.AuthorizationData;
import org.flab.deliveryplatform.common.auth.AuthorizationUseCase;
import org.flab.deliveryplatform.common.auth.UserType;
import org.flab.deliveryplatform.common.auth.exception.InvalidAuthorizationException;
import org.flab.deliveryplatform.owner.application.port.AuthorizationRepository;
import org.flab.deliveryplatform.owner.domain.auth.Authorization;

@RequiredArgsConstructor
public class AuthorizationService implements AuthorizationUseCase {

    private final AuthorizationRepository authorizationRepository;

    @Override
    public UserType getUserType() {
        return UserType.OWNER;
    }

    @Override
    public AuthorizationData getAuthorizationData(String token) {

        Authorization authorization = authorizationRepository.findById(token)
            .orElseThrow(InvalidAuthorizationException::new);

        return new AuthorizationData(
            authorization.getAccessToken(),
            authorization.getTokenType(),
            authorization.getOwnerId(),
            authorization.getIssueDate(),
            authorization.getAccessTokenExpiredTimeSecs()
        );
    }

}
