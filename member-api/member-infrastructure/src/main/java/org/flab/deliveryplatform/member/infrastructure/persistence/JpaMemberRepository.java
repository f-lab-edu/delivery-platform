package org.flab.deliveryplatform.member.infrastructure.persistence;

import java.util.Optional;
import org.flab.deliveryplatform.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Optional<Member> findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);
}
