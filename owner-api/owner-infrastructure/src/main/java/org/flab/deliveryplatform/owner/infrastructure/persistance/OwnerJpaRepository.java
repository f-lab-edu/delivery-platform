package org.flab.deliveryplatform.owner.infrastructure.persistance;

import org.flab.deliveryplatform.owner.domain.Owner;
import org.flab.deliveryplatform.owner.domain.OwnerId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerJpaRepository extends JpaRepository<Owner, OwnerId> {

    boolean existsByEmail(String email);

}
