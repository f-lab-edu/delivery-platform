package org.flab.deliveryplatform.server.outbox;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.event.OutBoxEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OutBoxEventHandler {

    private final JpaOutBoxRepository jpaOutBoxRepository;

    @EventListener
    public void handleOutBoxEvent(OutBoxEvent event) {
        jpaOutBoxRepository.save(OutBox.from(event));
    }
}
