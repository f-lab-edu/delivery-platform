package org.flab.deliveryplatform.shop.application.port;

import java.util.List;
import org.flab.deliveryplatform.shop.domain.Shop;

public interface ShopRepository {

    List<Shop> findAll();
}
