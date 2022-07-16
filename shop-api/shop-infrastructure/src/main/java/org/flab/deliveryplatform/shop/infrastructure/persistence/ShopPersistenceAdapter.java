package org.flab.deliveryplatform.shop.infrastructure.persistence;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.shop.application.port.ShopRepository;
import org.flab.deliveryplatform.shop.domain.Address;
import org.flab.deliveryplatform.shop.domain.PhoneNumber;
import org.flab.deliveryplatform.shop.domain.Shop;
import org.flab.deliveryplatform.shop.domain.ShopStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class ShopPersistenceAdapter implements ShopRepository {

    private final JpaShopRepository jpaShopRepository;

    @Override
    public List<Shop> findAll() {
        return jpaShopRepository.findAll();
    }
}
