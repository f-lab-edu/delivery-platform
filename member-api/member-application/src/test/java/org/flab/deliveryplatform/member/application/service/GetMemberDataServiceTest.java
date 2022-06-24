package org.flab.deliveryplatform.member.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

import java.util.Optional;
import java.util.UUID;
import org.flab.deliveryplatform.member.application.port.MemberRepository;
import org.flab.deliveryplatform.member.application.port.dto.MemberData;
import org.flab.deliveryplatform.member.application.port.exception.InvalidMemberInfoException;
import org.flab.deliveryplatform.member.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetMemberDataServiceTest {

    @InjectMocks
    private GetMemberDataService getMemberDataService;

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
    void getMemberDataByIdTest() {
        given(memberRepository.findById(savedMember.getId()))
            .willReturn(Optional.of(savedMember));

        MemberData memberData = getMemberDataService.getMemberData(
            savedMember.getId());

        assertThat(memberData.getId()).isEqualTo(savedMember.getId());
        assertThat(memberData.getEmail()).isEqualTo(savedMember.getEmail());
        assertThat(memberData.getNickname()).isEqualTo(savedMember.getNickname());
        assertThat(memberData.getPhoneNumber()).isEqualTo(savedMember.getPhoneNumber());
    }

    @Test
    void getMemberDataByInvalidIdTest() {
        given(memberRepository.findById(savedMember.getId()))
            .willReturn(Optional.empty());

        assertThatThrownBy(() -> getMemberDataService.getMemberData(savedMember.getId()))
            .isInstanceOf(InvalidMemberInfoException.class);
    }

    @Test
    void getMemberDataByEmailAndPasswordTest() {
        given(memberRepository.findByEmailAndPassword(
            savedMember.getEmail(), savedMember.getPassword()))
            .willReturn(Optional.of(savedMember));

        MemberData memberData = getMemberDataService.getMemberData(
            savedMember.getEmail(), savedMember.getPassword());

        assertThat(memberData.getId()).isEqualTo(savedMember.getId());
        assertThat(memberData.getEmail()).isEqualTo(savedMember.getEmail());
        assertThat(memberData.getNickname()).isEqualTo(savedMember.getNickname());
        assertThat(memberData.getPhoneNumber()).isEqualTo(savedMember.getPhoneNumber());
    }

    @Test
    void getMemberDataByInvalidEmailAndPasswordTest() {
        given(memberRepository.findByEmailAndPassword(
            savedMember.getEmail(), savedMember.getPassword()))
            .willReturn(Optional.empty());

        assertThatThrownBy(() -> getMemberDataService.getMemberData(
            savedMember.getEmail(), savedMember.getPassword()))
            .isInstanceOf(InvalidMemberInfoException.class);
    }
}
