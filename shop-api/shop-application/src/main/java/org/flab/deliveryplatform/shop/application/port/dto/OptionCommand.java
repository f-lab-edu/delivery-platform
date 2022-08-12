package org.flab.deliveryplatform.shop.application.port.dto;

import lombok.Getter;
import org.flab.deliveryplatform.shop.domain.Option;

@Getter
public class OptionCommand {

    private String name;

    private int price;

    private int displayOrder;

    public OptionCommand(String name, int price, int displayOrder) {
        this.name = name;
        this.price = price;
        this.displayOrder = displayOrder;
    }

    public Option toOption() {
        return Option.builder()
            .name(name)
            .price(price)
            .displayOrder(displayOrder)
            .build();
    }
}
