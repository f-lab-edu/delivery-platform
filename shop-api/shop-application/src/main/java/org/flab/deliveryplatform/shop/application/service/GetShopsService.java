package org.flab.deliveryplatform.shop.application.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.shop.application.port.GetShopsUseCase;
import org.flab.deliveryplatform.shop.application.port.ShopRepository;
import org.flab.deliveryplatform.shop.application.port.dto.ShopData;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetShopsService implements GetShopsUseCase {

    private final ShopRepository shopRepository;

    @Override
    public List<ShopData> getShops() {
        return shopRepository.findAll().stream()
            .map(ShopData::from)
            .collect(Collectors.toList());
    }
}
