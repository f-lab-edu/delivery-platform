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
public class Rider extends AggregateRoot {

    @Column(name = "rider_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Long userId;

    private double longitude;

    private double latitude;

    @Enumerated(EnumType.STRING)
    private RiderStatus status;

    @Builder
    private Rider(Long id, Long userId, double longitude, double latitude, RiderStatus status) {
        this.id = id;
        this.userId = userId;
        this.longitude = longitude;
        this.latitude = latitude;
        this.status = status;
    }
}
