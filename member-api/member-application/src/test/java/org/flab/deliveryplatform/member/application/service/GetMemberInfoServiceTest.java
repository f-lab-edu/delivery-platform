package org.flab.deliveryplatform.member.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

import java.util.Optional;
import org.flab.deliveryplatform.member.application.port.MemberPersistencePort;
import org.flab.deliveryplatform.member.application.port.dto.GetMemberInfoResult;
import org.flab.deliveryplatform.member.application.port.exception.InvalidMemberInfoException;
import org.flab.deliveryplatform.member.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetMemberInfoServiceTest {

    @InjectMocks
    private GetMemberInfoService getMemberInfoService;

    @Mock
    private MemberPersistencePort memberPersistencePort;

    private Member savedMember;

    @BeforeEach
    void init() {
        savedMember = Member.builder()
            .id(0L)
            .nickname("nickname")
            .email("test@gmail.com")
            .password("a12345678")
            .phoneNumber("010-1234-5678")
            .build();
    }

    @Test
    void getMemberInfoByIdTest() {
        given(memberPersistencePort.findById(savedMember.getId()))
            .willReturn(Optional.of(savedMember));

        GetMemberInfoResult memberInfoResult = getMemberInfoService.getMemberInfo(
            savedMember.getId());

        assertThat(memberInfoResult.getEmail()).isEqualTo(savedMember.getEmail());
        assertThat(memberInfoResult.getNickname()).isEqualTo(savedMember.getNickname());
        assertThat(memberInfoResult.getPhoneNumber()).isEqualTo(
            savedMember.getPhoneNumber());
    }

    @Test
    void getMemberInfoByInvalidIdTest() {
        given(memberPersistencePort.findById(savedMember.getId()))
            .willReturn(Optional.empty());

        assertThatThrownBy(() -> getMemberInfoService.getMemberInfo(savedMember.getId()))
            .isInstanceOf(InvalidMemberInfoException.class);
    }

    @Test
    void getMemberInfoByEmailAndPasswordTest() {
        given(memberPersistencePort.findByEmailAndPassword(
            savedMember.getEmail(), savedMember.getPassword()))
            .willReturn(Optional.of(savedMember));

        GetMemberInfoResult getMemberInfoResult = getMemberInfoService.getMemberInfo(
            savedMember.getEmail(), savedMember.getPassword());

        assertThat(getMemberInfoResult.getEmail()).isEqualTo(savedMember.getEmail());
        assertThat(getMemberInfoResult.getNickname()).isEqualTo(savedMember.getNickname());
        assertThat(getMemberInfoResult.getPhoneNumber()).isEqualTo(
            savedMember.getPhoneNumber());
    }

    @Test
    void getMemberInfoByInvalidEmailAndPasswordTest() {
        given(memberPersistencePort.findByEmailAndPassword(
            savedMember.getEmail(), savedMember.getPassword()))
            .willReturn(Optional.empty());

        assertThatThrownBy(() -> getMemberInfoService.getMemberInfo(
            savedMember.getEmail(), savedMember.getPassword()))
            .isInstanceOf(InvalidMemberInfoException.class);
    }
}
