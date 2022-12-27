package org.flab.deliveryplatform.delivery.infrastructure.persistence;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.delivery.application.port.RiderDispatcher;
import org.flab.deliveryplatform.delivery.domain.Delivery;
import org.flab.deliveryplatform.delivery.domain.Rider;
import org.flab.deliveryplatform.shop.domain.Location;
import org.flab.deliveryplatform.shop.domain.Shop;
import org.flab.deliveryplatform.shop.infrastructure.persistence.JpaShopRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SimpleRiderDispatcher implements RiderDispatcher {

    private static final int limitDistance = 200;

    private final JpaRiderRepository jpaRiderRepository;

    private final JpaShopRepository jpaShopRepository;

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void dispatch(Delivery delivery) {
        Location shopLocation = jpaShopRepository.findById(delivery.getShopId())
            .map(Shop::getLocation)
            .orElseThrow(() -> new IllegalArgumentException(
                "주어진 식별자에 해당하는 가게가 없습니다. 가게 ID = " + delivery.getShopId()
            ));

        jpaRiderRepository.findAll().stream()
            .filter(r -> isMatched(shopLocation, r))
            .map(r ->
                RiderMatchedEvent.builder()
                    .riderId(r.getId())
                    .deliveryId(delivery.getId())
                    .orderId(delivery.getOrderId())
                    .build()
            )
            .forEach(e -> eventPublisher.publishEvent(e));
    }

    private boolean isMatched(Location shopLocation, Rider rider) {
        return calculateDistance(rider.getLatitude(), rider.getLongitude(),
            shopLocation.getLatitude(), shopLocation.getLongitude()) < limitDistance;
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double distance =
            Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

        distance = Math.acos(distance);
        distance = rad2deg(distance);
        distance = distance * 60 * 1.1515 * 1609.344;

        return distance;
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
