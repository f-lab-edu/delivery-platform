package org.flab.deliveryplatform.member.application.service;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.auth.AuthorizationData;
import org.flab.deliveryplatform.common.auth.AuthorizationUseCase;
import org.flab.deliveryplatform.member.application.port.AuthorizationRepository;
import org.flab.deliveryplatform.member.application.port.exception.InvalidTokenException;
import org.flab.deliveryplatform.member.domain.authorization.Authorization;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements AuthorizationUseCase {

    private final AuthorizationRepository authorizationRepository;

    public AuthorizationData getAuthorizationData(String token) {
        Authorization authorization = authorizationRepository.findById(token)
            .orElseThrow(InvalidTokenException::new);

        return new AuthorizationData(
            authorization.getAccessToken(),
            authorization.getMemberId(),
            authorization.getIssueDate()
        );
    }

}
