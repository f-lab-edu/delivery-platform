package org.flab.deliveryplatform.member.infrastructure.persistence;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.member.application.port.MemberPersistencePort;
import org.flab.deliveryplatform.member.domain.Member;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberPersistenceAdaptor implements MemberPersistencePort {

    private final MemoryMemberRepository memberRepository;

    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public Optional<Member> findByEmailAndPassword(String email, String password) {
        return memberRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    @Override
    public boolean exists(String email) {
        return memberRepository.exists(email);
    }

    @Override
    public void delete(Member member) {
        memberRepository.delete(member);
    }
}
