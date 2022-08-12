package org.flab.deliveryplatform.shop.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.Optional;
import org.flab.deliveryplatform.shop.application.port.ShopRepository;
import org.flab.deliveryplatform.shop.application.port.dto.ShopDetailData;
import org.flab.deliveryplatform.shop.application.port.exception.ShopNotFoundException;
import org.flab.deliveryplatform.shop.domain.Shop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GetShopServiceTest {

    private GetShopService getShopService;

    private ShopRepository shopRepository = mock(ShopRepository.class);

    private Shop shop;

    private final Long existingShopId = 1L;
    private final Long notExistingShopId = -9999L;

    @BeforeEach
    void setUp() {
        getShopService = new GetShopService(shopRepository);

        shop = FakeShop.createShop(existingShopId);
    }

    @Test
    void getShopWithExistingShopId() {
        given(shopRepository.findById(existingShopId))
            .willReturn(Optional.of(shop));

        ShopDetailData shopData = getShopService.getShop(existingShopId);
        assertThat(shopData).isNotNull();
        assertThat(shopData.getId()).isEqualTo(shop.getId());
        assertThat(shopData.getName()).isEqualTo(shop.getName());
        assertThat(shopData.getAddress()).isEqualTo(shop.getAddress().toString());
        assertThat(shopData.getPhoneNumber()).isEqualTo(shop.getPhoneNumber().getPhoneNumber());
        assertThat(shopData.getStatus()).isEqualTo(shop.getStatus());
    }

    @Test
    void getShopWithNotExistingShopId() {
        given(shopRepository.findById(notExistingShopId))
            .willReturn(Optional.empty());

        assertThatThrownBy(() -> getShopService.getShop(notExistingShopId))
            .isInstanceOf(ShopNotFoundException.class);
    }
}
