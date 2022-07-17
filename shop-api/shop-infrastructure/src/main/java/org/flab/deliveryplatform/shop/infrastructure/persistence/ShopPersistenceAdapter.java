package org.flab.deliveryplatform.shop.infrastructure.persistence;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.shop.application.port.ShopRepository;
import org.flab.deliveryplatform.shop.domain.Shop;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ShopPersistenceAdapter implements ShopRepository {

    private final JpaShopRepository jpaShopRepository;

    @Override
    public List<Shop> findAll() {
        return jpaShopRepository.findAll();
    }

    @Override
    public Optional<Shop> findById(Long shopId) {
        return jpaShopRepository.findById(shopId);
    }

    @Override
    public Shop save(Shop shop) {
        return jpaShopRepository.save(shop);
    }
}
