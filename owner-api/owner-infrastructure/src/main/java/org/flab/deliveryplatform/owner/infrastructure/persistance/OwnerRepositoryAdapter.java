package org.flab.deliveryplatform.owner.infrastructure.persistance;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.owner.application.port.OwnerRepository;
import org.flab.deliveryplatform.owner.domain.Owner;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OwnerRepositoryAdapter implements OwnerRepository {

    private final OwnerJpaRepository ownerJpaRepository;

    @Override
    public Owner save(Owner owner) {
        return ownerJpaRepository.save(owner);
    }

    @Override
    public Optional<Owner> findByEmail(String email) {
        return ownerJpaRepository.findByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return ownerJpaRepository.existsByEmail(email);
    }
}
