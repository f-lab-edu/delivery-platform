package org.flab.deliveryplatform.shop.application.port;

import java.util.List;
import org.flab.deliveryplatform.shop.application.port.dto.ShopData;

public interface GetShopsUseCase {

    List<ShopData> getShops();
}
