package org.flab.deliveryplatform.order.infrastructure.factory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.event.OutBoxEvent;
import org.flab.deliveryplatform.order.application.port.OutBoxEventFactory;
import org.flab.deliveryplatform.order.domain.Order;
import org.flab.deliveryplatform.order.domain.OrderPayedEvent;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderOutBoxEventFactory implements OutBoxEventFactory<Order> {

    private final ObjectMapper objectMapper;

    @Override
    public List<OutBoxEvent> createOutBoxEvents(Order domain) {
        return domain.getOccurredEvents().stream()
            .map(e -> OutBoxEvent.builder()
                .aggregateType(Order.class.getSimpleName())
                .aggregateId(String.valueOf(domain.getId()))
                .eventType(OrderPayedEvent.class.getName())
                .payload(String.valueOf(objectMapper.convertValue(e, JsonNode.class)))
                .build()
            ).collect(Collectors.toList());
    }
}
