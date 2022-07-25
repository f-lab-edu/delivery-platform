package org.flab.deliveryplatform.shop.application.port.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.deliveryplatform.shop.domain.Menu;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CreateMenuCommand {

    private String name;

    private int price;

    public CreateMenuCommand(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Menu toMenu() {
        return Menu.builder()
            .name(name)
            .price(price)
            .build();
    }
}
