package org.flab.deliveryplatform.order.query.interfaces.eventlistener.event;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.deliveryplatform.common.event.Event;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class DeliveryCompletedApplicationEvent extends Event {

    private Long deliveryId;

    private Long orderId;

    private String status;
}
