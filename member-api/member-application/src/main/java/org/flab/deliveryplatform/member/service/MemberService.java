package org.flab.deliveryplatform.member.service;

import static org.flab.deliveryplatform.member.common.exception.MemberErrorCode.DUPLICATED_EMAIL;
import static org.flab.deliveryplatform.member.common.exception.MemberErrorCode.INVALID_MEMBER_INFO;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.exception.DeliveryPlatformException;
import org.flab.deliveryplatform.member.domain.Member;
import org.flab.deliveryplatform.member.persistence.MemberPersistencePort;
import org.flab.deliveryplatform.member.usecase.MemberInfoResult;
import org.flab.deliveryplatform.member.usecase.SignUpMemberCommand;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberPersistencePort memberPersistencePort;

    public Long signUp(SignUpMemberCommand signUpMemberCommand) {
        validateSignUp(signUpMemberCommand);

        Member saveMember = memberPersistencePort.save(
            Member.builder()
                .nickname(signUpMemberCommand.getNickname())
                .email(signUpMemberCommand.getEmail())
                .password(signUpMemberCommand.getPassword())
                .phoneNumber(signUpMemberCommand.getPhoneNumber())
                .build()
        );
        return saveMember.getId();
    }

    private void validateSignUp(SignUpMemberCommand signUpMemberCommand) {
        if (memberPersistencePort.exists(signUpMemberCommand.getEmail())) {
            throw new DeliveryPlatformException(DUPLICATED_EMAIL);
        }
    }

    public MemberInfoResult info(Long memberId) {
        return MemberInfoResult.from(
            memberPersistencePort.findById(memberId).orElseThrow(
                () -> new DeliveryPlatformException(INVALID_MEMBER_INFO))
        );
    }

    public MemberInfoResult findByEmailAndPassword(String email, String password) {
        return MemberInfoResult.from(
            memberPersistencePort.findByEmailAndPassword(email, password).orElseThrow(
                () -> new DeliveryPlatformException(INVALID_MEMBER_INFO))
        );
    }

}
