package org.flab.deliveryplatform.delivery.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.deliveryplatform.common.domain.AggregateRoot;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Delivery extends AggregateRoot {

    @Column(name = "delivery_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Long riderId;

    private Long orderId;

    private double longitude;

    private double latitude;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @Builder
    private Delivery(Long id, Long riderId, Long orderId, double longitude, double latitude) {
        this.id = id;
        this.riderId = riderId;
        this.orderId = orderId;
        this.longitude = longitude;
        this.latitude = latitude;
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

    public void dispatch() {
        this.status = DeliveryStatus.DISPATCHED;
        registerEvent(new DeliveryDispatchedEvent(id, status));
    }
}
