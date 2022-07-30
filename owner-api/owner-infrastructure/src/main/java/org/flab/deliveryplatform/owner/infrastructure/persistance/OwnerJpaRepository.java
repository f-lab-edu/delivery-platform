package org.flab.deliveryplatform.owner.infrastructure.persistance;

import java.util.Optional;
import org.flab.deliveryplatform.owner.domain.Owner;
import org.flab.deliveryplatform.owner.domain.OwnerId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerJpaRepository extends JpaRepository<Owner, OwnerId> {

    Optional<Owner> findByEmail(String email);

    boolean existsByEmail(String email);
}
