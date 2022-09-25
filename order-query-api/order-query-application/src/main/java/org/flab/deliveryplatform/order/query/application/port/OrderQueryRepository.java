package org.flab.deliveryplatform.order.query.application.port;

import java.util.List;
import java.util.Optional;
import org.flab.deliveryplatform.order.query.application.port.dto.OrderData;
import org.flab.deliveryplatform.order.query.domain.MyOrder;

public interface OrderQueryRepository {

    List<OrderData> findAllByMemberId(Long memberId);

    Optional<MyOrder> findByOrderId(Long orderId);
}
