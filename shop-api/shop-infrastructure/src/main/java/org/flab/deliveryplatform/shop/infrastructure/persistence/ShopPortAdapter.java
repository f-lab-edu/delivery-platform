package org.flab.deliveryplatform.shop.infrastructure.persistence;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.menu.appliciation.port.ExistsShopPort;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ShopPortAdapter implements ExistsShopPort {

    private final JpaShopRepository jpaShopRepository;

    @Override
    public boolean existsShopById(Long shopId) {
        return jpaShopRepository.existsById(shopId);
    }
}
