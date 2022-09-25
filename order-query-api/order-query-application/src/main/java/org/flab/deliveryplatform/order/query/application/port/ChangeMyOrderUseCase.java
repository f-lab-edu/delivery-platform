package org.flab.deliveryplatform.order.query.application.port;

public interface ChangeMyOrderUseCase {

    void changeMyOrderStatus(Long orderId, String status);

    void changeMyOrderDeliveryStatus(Long orderId, String deliveryStatus);
}
