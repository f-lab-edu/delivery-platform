package org.flab.deliveryplatform.menu.appliciation.port;

import java.util.List;
import org.flab.deliveryplatform.domain.Menu;
import org.flab.deliveryplatform.domain.ShopId;

public interface MenuRepository {

    Menu save(Menu menu);

    List<Menu> findAllByShopId(ShopId shopId);
}
