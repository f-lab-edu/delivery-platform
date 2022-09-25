package org.flab.deliveryplatform.order.query.application.port;

import java.util.List;
import java.util.Optional;
import org.flab.deliveryplatform.order.query.application.port.dto.CreateMyOrderCommand;
import org.flab.deliveryplatform.order.query.application.port.dto.OrderData;
import org.flab.deliveryplatform.order.query.domain.MyOrder;

public interface MyOrderRepository {

    List<OrderData> findAllByMemberId(Long memberId);

    Optional<MyOrder> findByOrderId(Long orderId);

    MyOrder save(CreateMyOrderCommand command);
}
