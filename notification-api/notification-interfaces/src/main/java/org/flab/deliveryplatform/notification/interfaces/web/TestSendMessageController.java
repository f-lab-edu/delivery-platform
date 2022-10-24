package org.flab.deliveryplatform.notification.interfaces.web;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.notification.application.port.dto.ShopNotificationMessage;
import org.flab.deliveryplatform.notification.application.service.ShopSendMessageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/notifications")
@RequiredArgsConstructor
public class TestSendMessageController {

    private final ShopSendMessageService shopSendMessageService;

    @PostMapping
    public void send(@RequestBody ShopNotificationMessage message) {
        shopSendMessageService.sendMessage(message);
    }

}
