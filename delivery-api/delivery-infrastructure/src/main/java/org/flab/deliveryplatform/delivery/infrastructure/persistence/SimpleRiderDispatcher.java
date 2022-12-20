package org.flab.deliveryplatform.delivery.infrastructure.persistence;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.delivery.application.port.RiderDispatcher;
import org.flab.deliveryplatform.delivery.domain.Delivery;
import org.flab.deliveryplatform.delivery.domain.Rider;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class SimpleRiderDispatcher implements RiderDispatcher {

    private final JpaRiderRepository jpaRiderRepository;

    @Override
    public void dispatch(Delivery delivery) {
        List<Rider> riders = jpaRiderRepository.findAll();

        // 1. 가게 위치, 라이더 위치, 상태 비교하여 라이더 선택
        // 2. 이벤트 발행
    }
}
