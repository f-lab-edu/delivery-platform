package org.flab.deliveryplatform.member.application.service;

import static org.mockito.BDDMockito.given;

import java.util.Optional;
import java.util.UUID;
import org.assertj.core.api.Assertions;
import org.flab.deliveryplatform.member.application.port.MemberRepository;
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
    private MemberRepository memberRepository;

    private Member savedMember;

    @BeforeEach
    void init() {
        savedMember = Member.builder()
            .id(Long.MAX_VALUE)
            .nickname(UUID.randomUUID().toString().substring(0, 20))
            .email(UUID.randomUUID().toString().substring(0, 20) + "@gmail.com")
            .password("a12345678")
            .phoneNumber("010-1234-5678")
            .build();
    }

    @Test
    void withdrawTest() {
        given(memberRepository.findByEmailAndPassword(
            savedMember.getEmail(), savedMember.getPassword()))
            .willReturn(Optional.of(savedMember));

        WithdrawMemberCommand withdrawMemberCommand = new WithdrawMemberCommand(
            savedMember.getEmail(), savedMember.getPassword());

        withdrawMemberService.withdraw(withdrawMemberCommand);
    }

    @Test
    void withdrawWithInvalidInfoTest() {
        given(memberRepository.findByEmailAndPassword(
            savedMember.getEmail(), savedMember.getPassword()))
            .willReturn(Optional.empty());

        WithdrawMemberCommand withdrawMemberCommand = new WithdrawMemberCommand(
            savedMember.getEmail(), savedMember.getPassword());

        Assertions.assertThatThrownBy(() -> withdrawMemberService.withdraw(withdrawMemberCommand))
            .isInstanceOf(InvalidMemberInfoException.class);
    }
}
