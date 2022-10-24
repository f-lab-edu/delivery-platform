package org.flab.deliveryplatform.shop.application.port.dto;

import lombok.Getter;
import org.flab.deliveryplatform.shop.domain.Menu;

@Getter
public class CreateMenuCommand {

    private String name;

    private int displayOrder;

    private String description;

    public CreateMenuCommand(String name, int displayOrder, String description) {
        this.name = name;
        this.displayOrder = displayOrder;
        this.description = description;
    }

    public Menu toMenu() {
        return Menu.builder()
            .name(name)
            .displayOrder(displayOrder)
            .description(description)
            .build();
    }
}
