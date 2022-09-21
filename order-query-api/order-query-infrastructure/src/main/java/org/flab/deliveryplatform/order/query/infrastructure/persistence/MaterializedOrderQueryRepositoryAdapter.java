package org.flab.deliveryplatform.order.query.infrastructure.persistence;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.order.query.application.OrderQueryRepository;
import org.flab.deliveryplatform.order.query.application.dto.OrderData;
import org.flab.deliveryplatform.order.query.application.dto.OrderData.OrderLineItemData;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Primary
@Repository
public class MaterializedOrderQueryRepositoryAdapter implements OrderQueryRepository {

    private final JpaMyOrderRepository jpaMyOrderRepository;

    @Override
    public List<OrderData> findAllByMemberId(Long memberId) {
        return jpaMyOrderRepository.findAllById(memberId).stream()
            .map(o -> {
                List<OrderLineItemData> orderLineItemData = o.getMyOrderLineItems().stream()
                    .map(ol -> new OrderLineItemData(ol.getName(), ol.getCount(), ol.getTotalPrice()))
                    .collect(Collectors.toList());

                return OrderData.builder()
                    .id(o.getOrderId())
                    .orderLineItems(orderLineItemData)
                    .status(o.getStatus())
                    .totalPrice(o.getTotalPrice())
                    .shopName(o.getShopName())
                    .deliveryStatus(o.getDeliveryStatus())
                    .build();
            })
            .collect(Collectors.toList());
    }
}
