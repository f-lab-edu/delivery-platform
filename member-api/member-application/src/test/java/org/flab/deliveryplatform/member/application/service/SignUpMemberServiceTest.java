package org.flab.deliveryplatform.member.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import org.flab.deliveryplatform.member.application.port.MemberPersistencePort;
import org.flab.deliveryplatform.member.application.port.dto.SignUpMemberCommand;
import org.flab.deliveryplatform.member.application.port.dto.SignUpMemberResult;
import org.flab.deliveryplatform.member.application.port.exception.DuplicatedEmailException;
import org.flab.deliveryplatform.member.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SignUpMemberServiceTest {

    @InjectMocks
    private SignUpMemberService signUpMemberService;

    @Mock
    private MemberPersistencePort memberPersistencePort;

    private SignUpMemberCommand signUpMemberCommand;

    private Member savedMember;

    @BeforeEach
    void init() {
        signUpMemberCommand = new SignUpMemberCommand(
            "nickname", "test@gmail.com", "a12345678", "010-1234-5678"
        );

        savedMember = Member.builder()
            .id(0L)
            .nickname(signUpMemberCommand.getNickname())
            .email(signUpMemberCommand.getEmail())
            .password(signUpMemberCommand.getPassword())
            .phoneNumber(signUpMemberCommand.getPhoneNumber())
            .build();
    }

    @Test
    void signUpTest() {
        given(memberPersistencePort.save(any(Member.class)))
            .willReturn(savedMember);

        SignUpMemberResult signUpMemberResult = signUpMemberService.signUp(signUpMemberCommand);

        assertThat(signUpMemberResult.getId()).isEqualTo(savedMember.getId());

    }

    @Test
    void signUpWithDuplicatedEmailTest() {
        given(memberPersistencePort.exists(savedMember.getEmail()))
            .willReturn(true);

        assertThatThrownBy(() -> signUpMemberService.signUp(signUpMemberCommand))
            .isInstanceOf(DuplicatedEmailException.class);
    }
}
