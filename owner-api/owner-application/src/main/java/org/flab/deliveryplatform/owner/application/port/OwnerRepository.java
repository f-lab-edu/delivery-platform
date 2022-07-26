package org.flab.deliveryplatform.owner.application.port;

import org.flab.deliveryplatform.owner.domain.Owner;

public interface OwnerRepository {

    Owner save(Owner owner);

    boolean existsByEmail(String email);
}
