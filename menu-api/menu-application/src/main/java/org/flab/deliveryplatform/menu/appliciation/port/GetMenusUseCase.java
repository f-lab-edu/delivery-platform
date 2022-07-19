package org.flab.deliveryplatform.menu.appliciation.port;

import java.util.List;
import org.flab.deliveryplatform.menu.appliciation.port.dto.MenuData;

public interface GetMenusUseCase {

    List<MenuData> getMenus(Long shopId);
}
