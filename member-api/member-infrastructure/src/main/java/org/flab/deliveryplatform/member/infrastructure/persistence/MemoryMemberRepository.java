package org.flab.deliveryplatform.member.infrastructure.persistence;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.flab.deliveryplatform.member.domain.Member;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryMemberRepository {

    private final AtomicLong sequence = new AtomicLong();
    private final Map<Long, Member> store = new ConcurrentHashMap<>();

    {
        // TODO: DB에 저장 후 삭제.
        Member saveMember = Member.builder()
            .id(sequence.incrementAndGet())
            .nickname("test")
            .email("test@test.com")
            .password("test1234")
            .phoneNumber("010-1234-1234")
            .build();
        store.put(saveMember.getId(), saveMember);
    }

    public Member save(Member member) {
        Member saveMember = Member.builder()
            .id(sequence.incrementAndGet())
            .nickname(member.getNickname())
            .email(member.getEmail())
            .password(member.getPassword())
            .phoneNumber(member.getPhoneNumber())
            .build();
        store.put(saveMember.getId(), saveMember);
        return saveMember;
    }

    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public Optional<Member> findByEmailAndPassword(String email, String password) {
        return store.values()
            .stream()
            .filter(m -> m.getEmail().equals(email))
            .filter(m -> m.getPassword().equals(password))
            .findAny();
    }

    public Optional<Member> findByEmail(String email) {
        return store.values()
            .stream()
            .filter(m -> m.getEmail().equals(email))
            .findAny();
    }

    public boolean exists(String email) {
        return store.values()
            .stream()
            .anyMatch(m -> m.getEmail().equals(email));
    }

    public void clear() {
        store.clear();
    }

    public void delete(Member member) {
        store.remove(member.getId());
    }
}
