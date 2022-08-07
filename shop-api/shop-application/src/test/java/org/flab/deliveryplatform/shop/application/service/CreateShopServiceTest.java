package org.flab.deliveryplatform.shop.application.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.flab.deliveryplatform.shop.application.port.ShopRepository;
import org.flab.deliveryplatform.shop.application.port.dto.AddressDto;
import org.flab.deliveryplatform.shop.application.port.dto.CreateShopCommand;
import org.flab.deliveryplatform.shop.domain.Shop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreateShopServiceTest {

    private CreateShopService createShopService;

    private ShopRepository shopRepository = mock(ShopRepository.class);

    private Shop shop;

    private CreateShopCommand createShopCommand;

    @BeforeEach
    void setUp() {
        createShopService = new CreateShopService(shopRepository);
        shop = FakeShop.createShop(1L);

        createShopCommand = new CreateShopCommand(
            "name",
            new AddressDto("zipCode", "country", "state", "city", "street"),
            "01011112222",
            10000
        );
    }

    @Test
    void createShop() {
        given(shopRepository.save(any(Shop.class)))
            .willReturn(shop);

        createShopService.createShop(createShopCommand);

        verify(shopRepository).save(any(Shop.class));
    }
}
