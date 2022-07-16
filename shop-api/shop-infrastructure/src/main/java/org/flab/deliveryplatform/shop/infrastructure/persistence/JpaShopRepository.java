package org.flab.deliveryplatform.shop.infrastructure.persistence;

import org.flab.deliveryplatform.shop.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaShopRepository extends JpaRepository<Shop, Long> {

}
