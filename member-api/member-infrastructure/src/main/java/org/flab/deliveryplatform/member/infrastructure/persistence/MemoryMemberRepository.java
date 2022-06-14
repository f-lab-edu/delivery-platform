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

    public Member save(Member member) {
        member.setId(sequence.getAndIncrement());
        store.put(member.getId(), member);
        return member;
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

    public boolean exists(String email) {
        return store.values()
            .stream()
            .anyMatch(m -> m.getEmail().equals(email));
    }

    public void delete(Member member) {
        store.remove(member.getId());
    }
}
