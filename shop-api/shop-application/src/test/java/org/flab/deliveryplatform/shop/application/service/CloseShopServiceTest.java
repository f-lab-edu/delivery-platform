package org.flab.deliveryplatform.shop.application.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Optional;
import org.flab.deliveryplatform.shop.application.port.ShopRepository;
import org.flab.deliveryplatform.shop.application.port.exception.ShopNotFoundException;
import org.flab.deliveryplatform.shop.domain.Shop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CloseShopServiceTest {

    private CloseShopService closeShopService;

    private ShopRepository shopRepository = mock(ShopRepository.class);

    private Shop shop;

    private final Long existingShopId = 1L;
    private final Long notExistingShopId = -9999L;

    @BeforeEach
    void setUp() {
        closeShopService = new CloseShopService(shopRepository);

        shop = FakeShop.createShop(existingShopId);
    }

    @Test
    void closeShop() {
        given(shopRepository.findById(existingShopId))
            .willReturn(Optional.of(shop));

        given(shopRepository.save(any(Shop.class)))
            .willReturn(shop);

        closeShopService.closeShop(existingShopId);

        verify(shopRepository).save(any(Shop.class));
    }

    @Test
    void closeShopWithNotExistingShopId() {
        given(shopRepository.findById(notExistingShopId))
            .willReturn(Optional.empty());

        assertThatThrownBy(() -> closeShopService.closeShop(notExistingShopId))
            .isInstanceOf(ShopNotFoundException.class);
    }
}
