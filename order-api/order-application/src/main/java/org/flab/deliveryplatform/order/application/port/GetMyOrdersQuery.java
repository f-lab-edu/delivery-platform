package org.flab.deliveryplatform.order.application.port;

import java.util.List;
import org.flab.deliveryplatform.order.application.port.dto.OrderData;

public interface GetMyOrdersQuery {

    List<OrderData> getMyOrders(Long memberId);
}
