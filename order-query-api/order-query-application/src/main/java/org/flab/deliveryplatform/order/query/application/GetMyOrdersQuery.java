package org.flab.deliveryplatform.order.query.application;

import java.util.List;
import org.flab.deliveryplatform.order.query.application.dto.OrderData;

public interface GetMyOrdersQuery {

    List<OrderData> getMyOrders(Long memberId);
}
