package org.flab.deliveryplatform.member.application.service;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.member.application.port.LoginMemberUseCase;
import org.flab.deliveryplatform.member.application.port.MemberRepository;
import org.flab.deliveryplatform.member.application.port.dto.CreateTokenCommand;
import org.flab.deliveryplatform.member.application.port.dto.LoginMemberCommand;
import org.flab.deliveryplatform.member.application.port.dto.TokenData;
import org.flab.deliveryplatform.member.application.port.exception.InvalidMemberInfoException;
import org.flab.deliveryplatform.member.application.service.provider.TokenProvider;
import org.flab.deliveryplatform.member.application.service.utils.EncryptUtils;
import org.flab.deliveryplatform.member.domain.Member;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginMemberService implements LoginMemberUseCase {

    private final MemberRepository memberRepository;

    private final TokenProvider tokenProvider;

    private final EncryptUtils encryptUtils;

    @Override
    public TokenData login(LoginMemberCommand command) {
        Member member = memberRepository.findByEmail(command.getEmail())
            .orElseThrow(InvalidMemberInfoException::new);

        if (!encryptUtils.isMatch(command.getPassword(), member.getPassword())) {
            throw new InvalidMemberInfoException();
        }

        return tokenProvider.generateToken(new CreateTokenCommand(member.getId()));
    }
}
