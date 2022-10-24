package org.flab.deliveryplatform.server.event.outbox;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.event.Event;
import org.flab.deliveryplatform.delivery.domain.Delivery;
import org.flab.deliveryplatform.delivery.domain.DeliveryCompletedEvent;
import org.flab.deliveryplatform.order.domain.Order;
import org.flab.deliveryplatform.order.domain.event.OrderCreatedEvent;
import org.flab.deliveryplatform.order.domain.event.OrderPayedEvent;
import org.flab.deliveryplatform.order.domain.event.OrderPayedNotificationEvent;
import org.flab.deliveryplatform.order.domain.event.OrderStatusChangedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OutBoxEventHandler {

    private final JpaOutBoxRepository jpaOutBoxRepository;

    private final ObjectMapper objectMapper;

    @EventListener
    public void handleOrderPayedEvent(OrderPayedEvent event) {
        OutBox outBox = OutBox.builder()
            .aggregateType(Order.class.getSimpleName())
            .aggregateId(String.valueOf(event.getOrderId()))
            .eventType(event.getClass().getSimpleName())
            .payload(getPayload(event))
            .build();

        jpaOutBoxRepository.save(outBox);
    }

    @EventListener
    public void handleOrderPayedNotificationEvent(OrderPayedNotificationEvent event) {
        OutBox outBox = OutBox.builder()
            .aggregateType(Order.class.getSimpleName())
            .aggregateId(String.valueOf(event.getOrderId()))
            .eventType(event.getClass().getSimpleName())
            .payload(getPayload(event))
            .build();

        jpaOutBoxRepository.save(outBox);
    }

    @EventListener
    public void handleOrderStatusChangedEvent(OrderStatusChangedEvent event) {
        OutBox outBox = OutBox.builder()
            .aggregateType(Order.class.getSimpleName())
            .aggregateId(String.valueOf(event.getOrderId()))
            .eventType(event.getClass().getSimpleName())
            .payload(getPayload(event))
            .build();

        jpaOutBoxRepository.save(outBox);
    }

    @EventListener
    public void handleOrderCreatedEvent(OrderCreatedEvent event) {
        OutBox outBox = OutBox.builder()
            .aggregateType(Order.class.getSimpleName())
            .aggregateId(String.valueOf(event.getOrderId()))
            .eventType(event.getClass().getSimpleName())
            .payload(getPayload(event))
            .build();

        jpaOutBoxRepository.save(outBox);
    }

    @EventListener
    public void handleOrderCreatedEvent(DeliveryCompletedEvent event) {
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

    private String getJsonPayload(Event event) {
        JsonNode jsonNode = objectMapper.convertValue(event, JsonNode.class);
        return jsonNode.toString();
    }
}
