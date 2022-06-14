package org.flab.deliveryplatform.member.persistence;

import java.util.Optional;
import org.flab.deliveryplatform.member.domain.Member;

public interface MemberPersistencePort {

    Member save(Member member);

    Optional<Member> findById(Long id);

    Optional<Member> findByEmailAndPassword(String email, String password);

    boolean exists(String email);
}
