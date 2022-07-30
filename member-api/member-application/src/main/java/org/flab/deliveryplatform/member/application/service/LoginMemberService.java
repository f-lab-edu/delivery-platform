package org.flab.deliveryplatform.member.application.service;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.auth.AuthorizationData;
import org.flab.deliveryplatform.member.application.port.AuthorizationRepository;
import org.flab.deliveryplatform.member.application.port.EncryptManager;
import org.flab.deliveryplatform.member.application.port.LoginMemberUseCase;
import org.flab.deliveryplatform.member.application.port.MemberRepository;
import org.flab.deliveryplatform.member.application.port.TokenProvider;
import org.flab.deliveryplatform.member.application.port.dto.CreateTokenCommand;
import org.flab.deliveryplatform.member.application.port.dto.LoginMemberCommand;
import org.flab.deliveryplatform.member.application.port.exception.InvalidMemberInfoException;
import org.flab.deliveryplatform.member.domain.Member;
import org.flab.deliveryplatform.member.domain.authorization.Authorization;
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

        Authorization authorization = authorizationRepository.save(
            Authorization.builder()
                .accessToken(token)
                .memberId(member.getId())
                .issueDate(LocalDateTime.now())
                .build()
        );

        return new AuthorizationData(
            authorization.getAccessToken(),
            authorization.getMemberId(),
            authorization.getIssueDate()
        );
    }
}
