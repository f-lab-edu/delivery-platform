package org.flab.deliveryplatform.member.infrastructure.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.NoSuchElementException;
import java.util.UUID;
import org.flab.deliveryplatform.member.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {MemberRepositoryAdaptor.class, MemoryMemberRepository.class})
class MemberPersistenceAdaptorTest {

    @Autowired
    private MemberRepositoryAdaptor memberRepositoryAdaptor;

    @Autowired
    private MemoryMemberRepository memberRepository;

    private Member savedMember;

    @BeforeEach
    void init() {
        Member member = Member.builder()
            .nickname(UUID.randomUUID().toString().substring(0, 20))
            .email(UUID.randomUUID().toString().substring(0, 20) + "@gmail.com")
            .password("a12345678")
            .phoneNumber("010-1234-5678")
            .build();

        savedMember = memberRepositoryAdaptor.save(member);
    }

    @AfterEach
    void release() {
        memberRepository.delete(savedMember);
    }

    @Test
    void save() {
        Member member = Member.builder()
            .nickname(UUID.randomUUID().toString().substring(0, 20))
            .email(UUID.randomUUID().toString().substring(0, 20) + "@gmail.com")
            .password("a12345678")
            .phoneNumber("010-1234-5678")
            .build();

        Member savedMember = memberRepositoryAdaptor.save(member);

        assertThat(savedMember.getEmail()).isEqualTo(member.getEmail());
        assertThat(savedMember.getNickname()).isEqualTo(member.getNickname());
        assertThat(savedMember.getPassword()).isEqualTo(member.getPassword());
        assertThat(savedMember.getPhoneNumber()).isEqualTo(member.getPhoneNumber());
    }

    @Test
    void findById() {
        Member foundMember = memberRepositoryAdaptor.findById(savedMember.getId())
            .orElseThrow();

        assertThat(foundMember.getEmail()).isEqualTo(savedMember.getEmail());
        assertThat(foundMember.getNickname()).isEqualTo(savedMember.getNickname());
        assertThat(foundMember.getPassword()).isEqualTo(savedMember.getPassword());
        assertThat(foundMember.getPhoneNumber()).isEqualTo(savedMember.getPhoneNumber());

        assertThatThrownBy(
            () -> memberRepositoryAdaptor.findById(Long.MAX_VALUE).orElseThrow())
            .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void findByEmailAndPassword() {
        Member foundMember = memberRepositoryAdaptor.findByEmailAndPassword(savedMember.getEmail(),
                savedMember.getPassword())
            .orElseThrow();

        assertThat(foundMember.getEmail()).isEqualTo(savedMember.getEmail());
        assertThat(foundMember.getNickname()).isEqualTo(savedMember.getNickname());
        assertThat(foundMember.getPassword()).isEqualTo(savedMember.getPassword());
        assertThat(foundMember.getPhoneNumber()).isEqualTo(savedMember.getPhoneNumber());
    }

    @Test
    void findByInvalidEmailAndPassword() {
        assertThatThrownBy(
            () -> memberRepositoryAdaptor.findByEmailAndPassword(savedMember.getEmail(),
                savedMember.getPassword() + "1").orElseThrow())
            .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void delete() {
        memberRepository.delete(savedMember);
    }

}
