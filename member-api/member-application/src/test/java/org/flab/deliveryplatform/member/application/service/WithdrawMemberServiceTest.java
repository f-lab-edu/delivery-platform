package org.flab.deliveryplatform.member.application.service;

import static org.mockito.BDDMockito.given;

import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.flab.deliveryplatform.member.application.port.MemberPersistencePort;
import org.flab.deliveryplatform.member.application.port.dto.SignUpMemberCommand;
import org.flab.deliveryplatform.member.application.port.dto.WithdrawMemberCommand;
import org.flab.deliveryplatform.member.application.port.exception.InvalidMemberInfoException;
import org.flab.deliveryplatform.member.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class WithdrawMemberServiceTest {

    @InjectMocks
    private WithdrawMemberService withdrawMemberService;

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
    void withdrawTest() {
        given(memberPersistencePort.findByEmailAndPassword(
            savedMember.getEmail(), savedMember.getPassword()))
            .willReturn(Optional.of(savedMember));

        WithdrawMemberCommand withdrawMemberCommand = new WithdrawMemberCommand(
            savedMember.getEmail(), savedMember.getPassword());

        withdrawMemberService.withdraw(withdrawMemberCommand);
    }

    @Test
    void withdrawWithInvalidInfoTest() {
        given(memberPersistencePort.findByEmailAndPassword(
            savedMember.getEmail(), savedMember.getPassword()))
            .willReturn(Optional.empty());

        WithdrawMemberCommand withdrawMemberCommand = new WithdrawMemberCommand(
            savedMember.getEmail(), savedMember.getPassword());

        Assertions.assertThatThrownBy(() -> withdrawMemberService.withdraw(withdrawMemberCommand))
            .isInstanceOf(InvalidMemberInfoException.class);
    }
}
