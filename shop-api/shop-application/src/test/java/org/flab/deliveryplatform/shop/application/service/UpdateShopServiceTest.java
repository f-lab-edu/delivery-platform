package org.flab.deliveryplatform.shop.application.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Optional;
import org.flab.deliveryplatform.shop.application.port.ShopRepository;
import org.flab.deliveryplatform.shop.application.port.dto.AddressDTO;
import org.flab.deliveryplatform.shop.application.port.dto.UpdateShopCommand;
import org.flab.deliveryplatform.shop.application.port.exception.ShopNotFoundException;
import org.flab.deliveryplatform.shop.domain.Shop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UpdateShopServiceTest {

    private UpdateShopService updateShopService;

    private ShopRepository shopRepository = mock(ShopRepository.class);

    private Shop shop;

    private UpdateShopCommand updateShopCommand;

    private final Long existingShopId = 1L;
    private final Long notExistingShopId = -9999L;

    @BeforeEach
    void setUp() {
        updateShopService = new UpdateShopService(shopRepository);

        shop = FakeShop.createShop(existingShopId);

        updateShopCommand = new UpdateShopCommand(
            "name",
            new AddressDTO("zipCode", "country", "state", "city", "street"),
            "01011112222"
        );
    }

    @Test
    void updateShop() {
        given(shopRepository.findById(existingShopId))
            .willReturn(Optional.of(shop));

        given(shopRepository.save(any(Shop.class)))
            .willReturn(shop);

        updateShopService.updateShop(existingShopId, updateShopCommand);

        verify(shopRepository).save(any(Shop.class));
    }

    @Test
    void updateShopWithNotExistingShopId() {
        given(shopRepository.findById(notExistingShopId))
            .willReturn(Optional.empty());

        assertThatThrownBy(() -> updateShopService.updateShop(notExistingShopId, updateShopCommand))
            .isInstanceOf(ShopNotFoundException.class);
    }
}