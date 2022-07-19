package org.flab.deliveryplatform.menu.appliciation.port;

import org.flab.deliveryplatform.domain.Menu;

public interface MenuRepository {

    Menu save(Menu menu);
}
