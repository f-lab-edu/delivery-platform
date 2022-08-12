package org.flab.deliveryplatform.owner.application.port;

import java.util.Optional;
import org.flab.deliveryplatform.owner.domain.Owner;

public interface OwnerRepository {

    Owner save(Owner owner);

    Optional<Owner> findByEmail(String email);

    boolean existsByEmail(String email);
}
