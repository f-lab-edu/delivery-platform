package org.flab.deliveryplatform.order.query.infrastructure.persistence;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.delivery.infrastructure.persistence.JpaDeliveryRepository;
import org.flab.deliveryplatform.member.infrastructure.persistence.MemoryMemberRepository;
import org.flab.deliveryplatform.order.infrastructure.persistence.JpaOrderRepository;
import org.flab.deliveryplatform.order.query.application.OrderQueryRepository;
import org.flab.deliveryplatform.order.query.application.dto.OrderData;
import org.flab.deliveryplatform.order.query.application.dto.OrderData.OrderLineItemData;
import org.flab.deliveryplatform.shop.infrastructure.persistence.JpaShopRepository;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OrderQueryRepositoryAdapter implements OrderQueryRepository {

    private final JpaOrderRepository jpaOrderRepository;

    private final JpaDeliveryRepository jpaDeliveryRepository;

    private final JpaShopRepository jpaShopRepository;

    private final MemoryMemberRepository memoryMemberRepository;

    @Override
    public List<OrderData> findAllByMemberId(Long memberId) {
        return jpaOrderRepository.findAllByMemberId(memberId).stream()
            .map(o -> {
                List<OrderLineItemData> orderLineItemData = o.getOrderLineItems().stream()
                    .map(ol -> new OrderLineItemData(ol.getName(), ol.getCount(), ol.getTotalPrice()))
                    .collect(Collectors.toList());

                String shopName = jpaShopRepository.findById(o.getShopId())
                    .map(s -> s.getName())
                    .orElseThrow(() -> new IllegalArgumentException("매장이 존재하지 않습니다."));

                String deliveryStatus = jpaDeliveryRepository.findByOrderId(o.getId())
                    .map(d -> d.getStatus().name())
                    .orElse(null);

                return OrderData.builder()
                    .id(o.getId())
                    .orderLineItems(orderLineItemData)
                    .status(o.getStatus().name())
                    .totalPrice(o.getTotalPrice())
                    .shopName(shopName)
                    .deliveryStatus(deliveryStatus)
                    .build();
            })
            .collect(Collectors.toList());
    }
}
