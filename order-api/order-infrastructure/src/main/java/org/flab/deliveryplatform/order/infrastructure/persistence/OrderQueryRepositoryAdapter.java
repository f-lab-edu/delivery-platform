package org.flab.deliveryplatform.order.infrastructure.persistence;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.delivery.infrastructure.persistence.JpaDeliveryRepository;
import org.flab.deliveryplatform.member.infrastructure.persistence.MemoryMemberRepository;
import org.flab.deliveryplatform.order.application.port.OrderQueryRepository;
import org.flab.deliveryplatform.order.application.port.dto.OrderData;
import org.flab.deliveryplatform.order.application.port.dto.OrderData.DeliveryData;
import org.flab.deliveryplatform.order.application.port.dto.OrderData.MemberData;
import org.flab.deliveryplatform.order.application.port.dto.OrderData.OrderLineItemData;
import org.flab.deliveryplatform.order.application.port.dto.OrderData.ShopData;
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
                List<OrderLineItemData> orderLineItems = o.getOrderLineItems().stream()
                    .map(ol -> new OrderLineItemData(ol.getName(), ol.getCount(), ol.getTotalPrice()))
                    .collect(Collectors.toList());

                ShopData shop = jpaShopRepository.findById(o.getShopId())
                    .map(s -> new ShopData(s.getName(), s.getPhoneNumber().getPhoneNumber(), s.getAddress().toString()))
                    .orElseThrow(() -> new IllegalArgumentException("매장이 존재하지 않습니다."));

                DeliveryData delivery = jpaDeliveryRepository.findByOrderId(o.getId())
                    .map(d -> new DeliveryData(d.getStatus().name()))
                    .orElse(null);

                MemberData member = memoryMemberRepository.findById(o.getMemberId())
                    .map(m -> new MemberData(m.getNickname(), m.getPhoneNumber()))
                    .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

                return OrderData.builder()
                    .id(o.getId())
                    .orderLineItems(orderLineItems)
                    .status(o.getStatus().name())
                    .totalPrice(o.getTotalPrice())
                    .shop(shop)
                    .delivery(delivery)
                    .member(member)
                    .build();
            })
            .collect(Collectors.toList());
    }
}
