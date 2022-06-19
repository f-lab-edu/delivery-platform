package org.flab.deliveryplatform.member.application.service;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.member.application.port.MemberPersistencePort;
import org.flab.deliveryplatform.member.application.port.dto.GetMemberInfoResult;
import org.flab.deliveryplatform.member.application.port.dto.SignUpMemberCommand;
import org.flab.deliveryplatform.member.application.port.dto.SignUpMemberResult;
import org.flab.deliveryplatform.member.domain.Member;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberPersistencePort memberPersistencePort;

    public SignUpMemberResult signUp(SignUpMemberCommand signUpMemberCommand) {
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
            throw new DuplicatedEmailException("중복된 이메일 입니다.");
        }
    }

    public GetMemberInfoResult getMemberInfo(Long memberId) {
        return GetMemberInfoResult.from(
            memberPersistencePort.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("잘못된 회원 정보입니다."))
        );
    }

    public GetMemberInfoResult getMemberInfo(String email, String password) {
        return GetMemberInfoResult.from(
            memberPersistencePort.findByEmailAndPassword(email, password).orElseThrow(
                () -> new IllegalArgumentException("잘못된 회원 정보입니다."))
        );
    }

}
