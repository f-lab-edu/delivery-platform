package org.flab.deliveryplatform.shop.application.port.dto;

import lombok.Getter;
import org.flab.deliveryplatform.shop.domain.Menu;

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
