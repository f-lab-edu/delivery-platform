package org.flab.deliveryplatform.shop.application.port.dto;

import lombok.Getter;
import org.flab.deliveryplatform.shop.domain.Menu;

@Getter
public class CreateMenuCommand {

    private String name;

    private int displayOrder;

    public CreateMenuCommand(String name, int displayOrder) {
        this.name = name;
        this.displayOrder = displayOrder;
    }

    public Menu toMenu() {
        return Menu.builder()
            .name(name)
            .displayOrder(displayOrder)
            .build();
    }
}
