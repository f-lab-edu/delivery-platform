package org.flab.deliveryplatform.server.event.outbox;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.event.Event;
import org.flab.deliveryplatform.delivery.domain.Delivery;
import org.flab.deliveryplatform.delivery.domain.DeliveryNotMatchedApplicationEvent;
import org.flab.deliveryplatform.order.domain.Order;
import org.flab.deliveryplatform.order.domain.OrderPayedApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OutBoxEventHandler {

    private final JpaOutBoxRepository jpaOutBoxRepository;

    private final ObjectMapper objectMapper;

    @EventListener
    public void handleOrderPayedEvent(OrderPayedApplicationEvent event) {
        OutBox outBox = OutBox.builder()
            .aggregateType(Order.class.getSimpleName())
            .aggregateId(String.valueOf(event.getOrderId()))
            .eventType(event.getClass().getSimpleName())
            .payload(getPayload(event))
            .build();

        jpaOutBoxRepository.save(outBox);
    }

    @EventListener
    public void handleDeliveryNotMatchedApplicationEvent(DeliveryNotMatchedApplicationEvent event) {
        OutBox outBox = OutBox.builder()
            .aggregateType(Delivery.class.getSimpleName())
            .aggregateId(String.valueOf(event.getDeliveryId()))
            .eventType(event.getClass().getSimpleName())
            .payload(getPayload(event))
            .build();

        jpaOutBoxRepository.save(outBox);
    }

    private String getPayload(Event event) {
        String payload;
        try {
            payload = objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("이벤트 직렬화 실패", e);
        }
        return payload;
    }
}
