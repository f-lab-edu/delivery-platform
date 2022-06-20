package org.flab.deliveryplatform.member.application.service;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.member.application.port.MemberPersistencePort;
import org.flab.deliveryplatform.member.application.port.SignUpMemberUseCase;
import org.flab.deliveryplatform.member.application.port.dto.SignUpMemberCommand;
import org.flab.deliveryplatform.member.application.port.dto.SignUpMemberResult;
import org.flab.deliveryplatform.member.application.port.exception.DuplicatedEmailException;
import org.flab.deliveryplatform.member.domain.Member;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpMemberService implements SignUpMemberUseCase {

    private final MemberPersistencePort memberPersistencePort;

    @Override
    public SignUpMemberResult signUp(SignUpMemberCommand signUpMemberCommand)
        throws DuplicatedEmailException {
        validateSignUp(signUpMemberCommand);
        return SignUpMemberResult.from(
            memberPersistencePort.save(
                Member.builder()
                    .nickname(signUpMemberCommand.getNickname())
                    .email(signUpMemberCommand.getEmail())
                    .password(signUpMemberCommand.getPassword())
                    .phoneNumber(signUpMemberCommand.getPhoneNumber())
                    .build()
            )
        );
    }

    private void validateSignUp(SignUpMemberCommand signUpMemberCommand) {
        if (memberPersistencePort.exists(signUpMemberCommand.getEmail())) {
            throw new DuplicatedEmailException();
        }
    }
}
