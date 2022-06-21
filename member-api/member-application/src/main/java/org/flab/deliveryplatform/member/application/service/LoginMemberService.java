package org.flab.deliveryplatform.member.application.service;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.member.application.port.LoginMemberUseCase;
import org.flab.deliveryplatform.member.application.port.MemberPersistencePort;
import org.flab.deliveryplatform.member.application.port.dto.LoginMemberCommand;
import org.flab.deliveryplatform.member.application.port.dto.TokenData;
import org.flab.deliveryplatform.member.application.port.exception.InvalidMemberInfoException;
import org.flab.deliveryplatform.member.application.service.provider.TokenProvider;
import org.flab.deliveryplatform.member.domain.Member;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginMemberService implements LoginMemberUseCase {

    private final MemberPersistencePort memberPersistencePort;

    private final TokenProvider tokenProvider;

    @Override
    public TokenData login(LoginMemberCommand command) {
        Member member = memberPersistencePort.findByEmail(command.getEmail())
                .orElseThrow(InvalidMemberInfoException::new);

        if (!member.authenticate(command.getPassword())) {
            throw new InvalidMemberInfoException("주어진 회원의 비밀번호가 잘못되었습니다.");
        }

        return tokenProvider.generateToken(member);
    }
}
