package org.flab.deliveryplatform.order.query.application.service;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.order.query.application.port.CreateMyOrderUseCase;
import org.flab.deliveryplatform.order.query.application.port.MyOrderRepository;
import org.flab.deliveryplatform.order.query.application.port.dto.CreateMyOrderCommand;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateMyOrderService implements CreateMyOrderUseCase {

    private final MyOrderRepository myOrderRepository;

    @Transactional
    @Override
    public void createMyOrder(CreateMyOrderCommand command) {
        myOrderRepository.save(command);
    }
}
