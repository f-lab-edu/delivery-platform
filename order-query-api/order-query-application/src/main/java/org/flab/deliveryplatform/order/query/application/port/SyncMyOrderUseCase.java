package org.flab.deliveryplatform.order.query.application.port;

import org.flab.deliveryplatform.order.query.application.port.dto.SyncMyOrderCommand;

public interface SyncMyOrderUseCase {

    void syncMyOrder(SyncMyOrderCommand command);

    void syncMyOrderStatus(Long orderId, String status);

    void syncMyOrderDeliveryStatus(Long orderId, String deliveryStatus);
}
