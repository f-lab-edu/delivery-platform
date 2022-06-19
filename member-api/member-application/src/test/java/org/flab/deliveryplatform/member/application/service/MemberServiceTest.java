package org.flab.deliveryplatform.member.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

import java.util.Optional;
import org.flab.deliveryplatform.member.application.port.MemberPersistencePort;
import org.flab.deliveryplatform.member.application.port.dto.GetMemberInfoResult;
import org.flab.deliveryplatform.member.application.port.dto.SignUpMemberCommand;
import org.flab.deliveryplatform.member.application.port.dto.SignUpMemberResult;
import org.flab.deliveryplatform.member.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

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
        given(memberPersistencePort.save(BDDMockito.any(Member.class)))
            .willReturn(savedMember);

        SignUpMemberResult signUpMemberResult = memberService.signUp(signUpMemberCommand);

        assertThat(signUpMemberResult.getId()).isEqualTo(savedMember.getId());

    }

    @Test
    void duplicatedEmailTest() {
        given(memberPersistencePort.exists(savedMember.getEmail()))
            .willReturn(true);

        assertThatThrownBy(() -> memberService.signUp(signUpMemberCommand))
            .isInstanceOf(DuplicatedEmailException.class);
    }

    @Test
    void infoTest() {
        given(memberPersistencePort.findById(savedMember.getId()))
            .willReturn(Optional.of(savedMember));

        GetMemberInfoResult memberInfoResult = memberService.getMemberInfo(savedMember.getId());

        assertThat(memberInfoResult.getEmail()).isEqualTo(savedMember.getEmail());
        assertThat(memberInfoResult.getNickname()).isEqualTo(savedMember.getNickname());
        assertThat(memberInfoResult.getPhoneNumber()).isEqualTo(
            savedMember.getPhoneNumber());
    }

    @Test
    void findByEmailAndPassword() {
        given(memberPersistencePort.findByEmailAndPassword(
            savedMember.getEmail(), savedMember.getPassword()))
            .willReturn(Optional.of(savedMember));

        GetMemberInfoResult getMemberInfoResult = memberService.getMemberInfo(
            savedMember.getEmail(), savedMember.getPassword());

        assertThat(getMemberInfoResult.getEmail()).isEqualTo(savedMember.getEmail());
        assertThat(getMemberInfoResult.getNickname()).isEqualTo(savedMember.getNickname());
        assertThat(getMemberInfoResult.getPhoneNumber()).isEqualTo(
            savedMember.getPhoneNumber());
    }

    @Test
    void invalidMemberEmailAndPassword() {
        given(memberPersistencePort.findByEmailAndPassword(
            savedMember.getEmail(), savedMember.getPassword()))
            .willReturn(Optional.empty());

        assertThatThrownBy(() -> memberService.getMemberInfo(
            savedMember.getEmail(), savedMember.getPassword()))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
