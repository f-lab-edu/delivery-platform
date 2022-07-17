package org.flab.deliveryplatform.shop.application.port;

import java.util.List;
import java.util.Optional;
import org.flab.deliveryplatform.shop.domain.Shop;

public interface ShopRepository {

    List<Shop> findAll();

    Optional<Shop> findById(Long shopId);

    Shop save(Shop shop);
}
