package org.flab.deliveryplatform.shop.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.Optional;
import org.flab.deliveryplatform.shop.application.port.ShopRepository;
import org.flab.deliveryplatform.shop.application.port.dto.ShopData;
import org.flab.deliveryplatform.shop.application.port.exception.ShopNotFoundException;
import org.flab.deliveryplatform.shop.domain.Address;
import org.flab.deliveryplatform.shop.domain.PhoneNumber;
import org.flab.deliveryplatform.shop.domain.Shop;
import org.flab.deliveryplatform.shop.domain.ShopStatus;
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

        shop = Shop.builder()
            .id(existingShopId)
            .name("shop")
            .address(new Address("zipCode", "country", "state", "city", "street"))
            .phoneNumber(new PhoneNumber("01011112222"))
            .status(ShopStatus.READY)
            .build();
    }

    @Test
    void getShopWithExistingShopId() {
        given(shopRepository.findById(existingShopId))
            .willReturn(Optional.of(shop));

        ShopData shopData = getShopService.getShop(existingShopId);
        assertThat(shopData).isNotNull();
        assertThat(shopData.getShopId()).isEqualTo(shop.getId());
        assertThat(shopData.getName()).isEqualTo(shop.getName());
        assertThat(shopData.getAddress()).isEqualTo(shop.getAddress().toString());
        assertThat(shopData.getPhoneNumber()).isEqualTo(shop.getPhoneNumber().getNumber());
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
