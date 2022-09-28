package org.flab.deliveryplatform.order.application.port;

public interface CancelOrderUseCase {

    void cancelOrder(Long orderId);
}
