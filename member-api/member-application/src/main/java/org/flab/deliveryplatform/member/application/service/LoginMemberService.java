package org.flab.deliveryplatform.member.application.service;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.member.application.port.AuthorizationRepository;
import org.flab.deliveryplatform.member.application.port.EncryptManager;
import org.flab.deliveryplatform.member.application.port.LoginMemberUseCase;
import org.flab.deliveryplatform.member.application.port.MemberRepository;
import org.flab.deliveryplatform.member.application.port.dto.AuthorizationData;
import org.flab.deliveryplatform.member.application.port.dto.CreateTokenCommand;
import org.flab.deliveryplatform.member.application.port.dto.LoginMemberCommand;
import org.flab.deliveryplatform.member.application.port.exception.InvalidMemberInfoException;
import org.flab.deliveryplatform.member.application.service.provider.TokenProvider;
import org.flab.deliveryplatform.member.domain.Member;
import org.flab.deliveryplatform.member.domain.authorization.Authorization;
import org.flab.deliveryplatform.member.domain.authorization.AuthorizationId;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class LoginMemberService implements LoginMemberUseCase {

    private final MemberRepository memberRepository;

    private final AuthorizationRepository authorizationRepository;

    private final TokenProvider tokenProvider;

    private final EncryptManager encryptManager;

    @Override
    public AuthorizationData login(LoginMemberCommand command) {
        Member member = memberRepository.findByEmail(command.getEmail())
            .orElseThrow(InvalidMemberInfoException::new);

        if (!encryptManager.isMatch(command.getPassword(), member.getPassword())) {
            throw new InvalidMemberInfoException();
        }

        String token = tokenProvider.generateToken(new CreateTokenCommand(member.getId()));
        Authorization authorization = Authorization.builder()
            .authorizationId(new AuthorizationId(token))
            .memberId(member.getId())
            .build();

        Authorization savedAuthorization = authorizationRepository.save(authorization);

        return new AuthorizationData(savedAuthorization.getAuthorizationId().getId());
    }
}
