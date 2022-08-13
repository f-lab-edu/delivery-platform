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

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Delivery {

    @Column(name = "delivery_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Long orderId;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @Builder
    private Delivery(Long id, Long orderId, DeliveryStatus status) {
        this.id = id;
        this.orderId = orderId;
        this.status = status;
    }
}
