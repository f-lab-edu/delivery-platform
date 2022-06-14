package org.flab.deliveryplatform.member.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.flab.deliveryplatform.member.common.exception.MemberErrorCode.DUPLICATED_EMAIL;
import static org.flab.deliveryplatform.member.common.exception.MemberErrorCode.INVALID_MEMBER_INFO;
import static org.mockito.BDDMockito.given;

import java.util.Optional;
import org.flab.deliveryplatform.exception.DeliveryPlatformException;
import org.flab.deliveryplatform.member.application.persistence.MemberPersistencePort;
import org.flab.deliveryplatform.member.application.usecase.MemberInfoResult;
import org.flab.deliveryplatform.member.application.usecase.SignUpMemberCommand;
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
            .nickname(signUpMemberCommand.getNickname())
            .email(signUpMemberCommand.getEmail())
            .password(signUpMemberCommand.getPassword())
            .phoneNumber(signUpMemberCommand.getPhoneNumber())
            .build();

        savedMember.setId(0L);
    }

    @Test
    void signUpTest() {
        given(memberPersistencePort.save(BDDMockito.any(Member.class)))
            .willReturn(savedMember);

        Long memberId = memberService.signUp(signUpMemberCommand);

        assertThat(memberId).isEqualTo(savedMember.getId());

    }

    @Test
    void duplicatedEmailTest() {
        given(memberPersistencePort.exists(savedMember.getEmail()))
            .willReturn(true);

        assertThatThrownBy(() -> memberService.signUp(signUpMemberCommand))
            .isInstanceOf(DeliveryPlatformException.class)
            .hasFieldOrPropertyWithValue("errorCode", DUPLICATED_EMAIL);
    }

    @Test
    void infoTest() {
        given(memberPersistencePort.findById(savedMember.getId()))
            .willReturn(Optional.of(savedMember));

        MemberInfoResult memberInfoResult = memberService.info(savedMember.getId());

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

        MemberInfoResult memberInfoResult = memberService.findByEmailAndPassword(
            savedMember.getEmail(), savedMember.getPassword());

        assertThat(memberInfoResult.getEmail()).isEqualTo(savedMember.getEmail());
        assertThat(memberInfoResult.getNickname()).isEqualTo(savedMember.getNickname());
        assertThat(memberInfoResult.getPhoneNumber()).isEqualTo(
            savedMember.getPhoneNumber());
    }

    @Test
    void invalidMemberEmailAndPassword() {
        given(memberPersistencePort.findByEmailAndPassword(
            savedMember.getEmail(), savedMember.getPassword()))
            .willReturn(Optional.empty());

        assertThatThrownBy(() -> memberService.findByEmailAndPassword(
            savedMember.getEmail(), savedMember.getPassword()))
            .isInstanceOf(DeliveryPlatformException.class)
            .hasFieldOrPropertyWithValue("errorCode", INVALID_MEMBER_INFO);
    }
}
