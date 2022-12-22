package org.flab.deliveryplatform.delivery.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.deliveryplatform.common.domain.AggregateRoot;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Delivery extends AggregateRoot {

    @Column(name = "delivery_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Version
    private Long version;

    private Long riderId;

    private Long orderId;

    @Embedded
    private Location riderLocation;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @Builder
    private Delivery(Long id, Long riderId, Long orderId, Location riderLocation) {
        this.id = id;
        this.riderId = riderId;
        this.orderId = orderId;
        this.riderLocation = riderLocation;
        this.status = DeliveryStatus.BEFORE_DISPATCHED;
    }

    public static Delivery of(Long orderId) {
        return Delivery.builder()
                .orderId(orderId)
                .build();
    }

    public void complete() {
        this.status = DeliveryStatus.DELIVERED;
        registerEvent(new DeliveryCompletedEvent(id, orderId, status));
    }

    public void dispatch(Long riderId, Location riderLocation) {
        this.riderId = riderId;
        this.riderLocation = riderLocation;
        this.status = DeliveryStatus.DISPATCHED;
        registerEvent(new DeliveryDispatchedEvent(id, status));
    }
}
