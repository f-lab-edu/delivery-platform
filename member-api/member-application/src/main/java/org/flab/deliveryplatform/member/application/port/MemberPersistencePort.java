package org.flab.deliveryplatform.member.application.port;

import java.util.Optional;
import org.flab.deliveryplatform.member.domain.Member;

public interface MemberPersistencePort {

    Member save(Member member);

    Optional<Member> findById(Long id);

    Optional<Member> findByEmailAndPassword(String email, String password);

    Optional<Member> findByEmail(String email);

    boolean exists(String email);

    void delete(Member member);
}
