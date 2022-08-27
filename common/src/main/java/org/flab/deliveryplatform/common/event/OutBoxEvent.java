package org.flab.deliveryplatform.common.event;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OutBoxEvent {

    private String aggregateType;

    private String aggregateId;

    private String eventType;

    private String payload;

    @Builder
    private OutBoxEvent(String aggregateType, String aggregateId, String eventType, String payload) {
        this.aggregateType = aggregateType;
        this.aggregateId = aggregateId;
        this.eventType = eventType;
        this.payload = payload;
    }
}
