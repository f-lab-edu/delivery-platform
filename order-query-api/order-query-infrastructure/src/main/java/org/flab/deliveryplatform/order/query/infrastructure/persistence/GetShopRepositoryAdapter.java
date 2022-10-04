package org.flab.deliveryplatform.order.query.infrastructure.persistence;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.order.query.application.port.GetShopRepository;
import org.flab.deliveryplatform.order.query.domain.Shop;
import org.flab.deliveryplatform.shop.infrastructure.persistence.JpaShopRepository;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class GetShopRepositoryAdapter implements GetShopRepository {

    private final JpaShopRepository jpaShopRepository;

    @Override
    public Optional<Shop> findById(Long shopId) {
        return jpaShopRepository.findById(shopId)
            .map(s -> new Shop(s.getName()));
    }
}
