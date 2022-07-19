package org.flab.deliveryplatform.menu.infrastructure.persistence;

import java.util.List;
import org.flab.deliveryplatform.domain.Menu;
import org.flab.deliveryplatform.domain.ShopId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findAllByShopId(ShopId shopId);
}
