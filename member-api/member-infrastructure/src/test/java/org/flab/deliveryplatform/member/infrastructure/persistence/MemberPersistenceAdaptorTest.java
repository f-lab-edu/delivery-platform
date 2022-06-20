package org.flab.deliveryplatform.member.infrastructure.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.NoSuchElementException;
import org.flab.deliveryplatform.member.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {MemberPersistenceAdaptor.class, MemoryMemberRepository.class})
class MemberPersistenceAdaptorTest {

    @Autowired
    private MemberPersistenceAdaptor memberPersistenceAdaptor;

    @Autowired
    private MemoryMemberRepository memberRepository;

    private Member savedMember;

    @BeforeEach
    void init() {
        Member member = Member.builder()
            .nickname("nicknameInit")
            .email("nicknameInit@gmail.com")
            .password("a12345678")
            .phoneNumber("010-1234-5678")
            .build();

        savedMember = memberPersistenceAdaptor.save(member);
    }

    @AfterEach
    void release() {
        memberRepository.delete(savedMember);
    }

    @Test
    void save() {
        Member member = Member.builder()
            .nickname("nickname")
            .email("nickname@gmail.com")
            .password("a12345678")
            .phoneNumber("010-1234-5678")
            .build();

        Member savedMember = memberPersistenceAdaptor.save(member);

        assertThat(savedMember.getEmail()).isEqualTo(member.getEmail());
        assertThat(savedMember.getNickname()).isEqualTo(member.getNickname());
        assertThat(savedMember.getPassword()).isEqualTo(member.getPassword());
        assertThat(savedMember.getPhoneNumber()).isEqualTo(member.getPhoneNumber());
    }

    @Test
    void findById() {
        Member foundMember = memberPersistenceAdaptor.findById(savedMember.getId())
            .orElseThrow();

        assertThat(foundMember.getEmail()).isEqualTo(savedMember.getEmail());
        assertThat(foundMember.getNickname()).isEqualTo(savedMember.getNickname());
        assertThat(foundMember.getPassword()).isEqualTo(savedMember.getPassword());
        assertThat(foundMember.getPhoneNumber()).isEqualTo(savedMember.getPhoneNumber());

        assertThatThrownBy(
            () -> memberPersistenceAdaptor.findById(foundMember.getId() + 100L).orElseThrow())
            .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void findByEmailAndPassword() {
        Member foundMember = memberPersistenceAdaptor.findByEmailAndPassword(savedMember.getEmail(),
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
            () -> memberPersistenceAdaptor.findByEmailAndPassword(savedMember.getEmail(),
                savedMember.getPassword() + "1").orElseThrow())
            .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void delete() {
        memberRepository.delete(savedMember);
    }
    
}
