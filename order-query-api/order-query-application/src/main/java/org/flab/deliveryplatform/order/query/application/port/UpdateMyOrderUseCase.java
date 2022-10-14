package org.flab.deliveryplatform.order.query.application.port;

public interface UpdateMyOrderUseCase {
    
    void updateMyOrderStatus(Long orderId, String status);

    void updateMyOrderDeliveryStatus(Long orderId, String deliveryStatus);
}
