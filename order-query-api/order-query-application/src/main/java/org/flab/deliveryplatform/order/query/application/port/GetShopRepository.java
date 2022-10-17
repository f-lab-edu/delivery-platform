package org.flab.deliveryplatform.order.query.application.port;

import java.util.Optional;
import org.flab.deliveryplatform.order.query.domain.Shop;

public interface GetShopRepository {

    Optional<Shop> findById(Long shopId);
}
