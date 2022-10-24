package org.flab.deliveryplatform.notification.interfaces.event;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.notification.application.port.dto.ShopNotificationMessage;
import org.flab.deliveryplatform.notification.application.service.ShopSendMessageService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderPayedNotificationApplicationEventHandler {

    private final ShopSendMessageService shopSendMessageService;

    private final ObjectMapper objectMapper;

    @EventListener
    public void handle(OrderPayedNotificationApplicationEvent event) {
        JsonNode jsonNode = objectMapper.convertValue(event, JsonNode.class);
        ShopNotificationMessage message = new ShopNotificationMessage(event.getShopId(), jsonNode.toString());
        shopSendMessageService.sendMessage(message);
    }
}
