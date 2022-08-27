package org.flab.deliveryplatform.server.outbox;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.deliveryplatform.common.event.OutBoxEvent;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "outbox")
@Entity
public class OutBox {

    @Column(name = "outbox_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String aggregateType;

    private String aggregateId;

    private String eventType;

    @Lob
    private String payload;

    @Builder
    private OutBox(Long id, String aggregateType, String aggregateId, String eventType, String payload) {
        this.id = id;
        this.aggregateType = aggregateType;
        this.aggregateId = aggregateId;
        this.eventType = eventType;
        this.payload = payload;
    }

    public static OutBox from(OutBoxEvent event) {
        return OutBox.builder()
            .aggregateType(event.getAggregateType())
            .aggregateId(event.getAggregateId())
            .eventType(event.getEventType())
            .payload(event.getPayload())
            .build();
    }
}
