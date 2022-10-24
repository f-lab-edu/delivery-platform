package org.flab.deliveryplatform.shop.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.List;
import org.flab.deliveryplatform.shop.application.port.ShopRepository;
import org.flab.deliveryplatform.shop.domain.Shop;
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

        shop1 = FakeShop.createShop(1L, 1L);
        shop2 = FakeShop.createShop(2L, 1L);
    }

    @Test
    void getShops() {
        given(shopRepository.findAll())
            .willReturn(List.of(shop1, shop2));

        assertThat(getShopsService.getShops()).hasSize(2);
    }


}
