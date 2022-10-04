package org.flab.deliveryplatform.order.query.infrastructure.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.order.query.application.port.MyOrderRepository;
import org.flab.deliveryplatform.order.query.application.port.dto.OrderData;
import org.flab.deliveryplatform.order.query.application.port.dto.OrderData.OrderLineItemData;
import org.flab.deliveryplatform.order.query.domain.MyOrder;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Primary
@Repository
public class MaterializedMyOrderRepositoryAdapter implements MyOrderRepository {

    private final JpaMyOrderRepository jpaMyOrderRepository;

    @Override
    public List<OrderData> findAllByMemberId(Long memberId) {
        return jpaMyOrderRepository.findAllByMemberId(memberId).stream()
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

    @Override
    public Optional<MyOrder> findByOrderId(Long orderId) {
        return jpaMyOrderRepository.findByOrderId(orderId);
    }

    @Override
    public MyOrder save(MyOrder myOrder) {
        return jpaMyOrderRepository.save(myOrder);
    }
}
