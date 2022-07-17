package org.flab.deliveryplatform.shop.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.List;
import org.flab.deliveryplatform.shop.application.port.ShopRepository;
import org.flab.deliveryplatform.shop.domain.Address;
import org.flab.deliveryplatform.shop.domain.PhoneNumber;
import org.flab.deliveryplatform.shop.domain.Shop;
import org.flab.deliveryplatform.shop.domain.ShopStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GetShopsServiceTest {

    private GetShopsService getShopsService;

    private ShopRepository shopRepository = mock(ShopRepository.class);

    private Shop shop1;
    private Shop shop2;

    @BeforeEach
    void setUp() {
        getShopsService = new GetShopsService(shopRepository);

        shop1 = createShop(1L);
        shop2 = createShop(2L);
    }

    @Test
    void getShops() {
        given(shopRepository.findAll())
            .willReturn(List.of(shop1, shop2));

        assertThat(getShopsService.getShops()).hasSize(2);
    }

    private Shop createShop(Long id) {
        return Shop.builder()
            .id(id)
            .name("shop")
            .address(new Address("zipCode", "country", "state", "city", "street"))
            .phoneNumber(new PhoneNumber("01011112222"))
            .status(ShopStatus.READY)
            .build();
    }
}
