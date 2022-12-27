package org.flab.deliveryplatform.delivery.application.port;

import org.flab.deliveryplatform.delivery.domain.Delivery;

public interface RiderDispatcher {

    void dispatch(Delivery delivery);
}
