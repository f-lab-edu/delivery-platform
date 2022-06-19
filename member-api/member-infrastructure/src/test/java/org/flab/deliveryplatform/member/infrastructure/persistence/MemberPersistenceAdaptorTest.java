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

    private Member member;

    @BeforeEach
    void init() {
        member = Member.builder()
            .nickname("nickname")
            .email("test@gmail.com")
            .password("a12345678")
            .phoneNumber("010-1234-5678")
            .build();
    }

    @AfterEach
    void release() {
        memberRepository.clear();
    }

    @Test
    void save() {
        Member savedMember = memberPersistenceAdaptor.save(member);

        assertThat(savedMember.getEmail()).isEqualTo(member.getEmail());
        assertThat(savedMember.getNickname()).isEqualTo(member.getNickname());
        assertThat(savedMember.getPassword()).isEqualTo(member.getPassword());
        assertThat(savedMember.getPhoneNumber()).isEqualTo(member.getPhoneNumber());
    }

    @Test
    void findById() {
        Member savedMember = memberPersistenceAdaptor.save(member);

        Member foundMember = memberPersistenceAdaptor.findById(savedMember.getId())
            .orElseThrow();

        assertThat(foundMember.getEmail()).isEqualTo(member.getEmail());
        assertThat(foundMember.getNickname()).isEqualTo(member.getNickname());
        assertThat(foundMember.getPassword()).isEqualTo(member.getPassword());
        assertThat(foundMember.getPhoneNumber()).isEqualTo(member.getPhoneNumber());

        assertThatThrownBy(
            () -> memberPersistenceAdaptor.findById(foundMember.getId() + 100L).orElseThrow())
            .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void findByEmailAndPassword() {
        Member savedMember = memberPersistenceAdaptor.save(member);

        Member foundMember = memberPersistenceAdaptor.findByEmailAndPassword(savedMember.getEmail(),
                savedMember.getPassword())
            .orElseThrow();

        assertThat(foundMember.getEmail()).isEqualTo(member.getEmail());
        assertThat(foundMember.getNickname()).isEqualTo(member.getNickname());
        assertThat(foundMember.getPassword()).isEqualTo(member.getPassword());
        assertThat(foundMember.getPhoneNumber()).isEqualTo(member.getPhoneNumber());

        assertThatThrownBy(
            () -> memberPersistenceAdaptor.findByEmailAndPassword(savedMember.getEmail(),
                savedMember.getPassword() + "1").orElseThrow())
            .isInstanceOf(NoSuchElementException.class);


    }
}
