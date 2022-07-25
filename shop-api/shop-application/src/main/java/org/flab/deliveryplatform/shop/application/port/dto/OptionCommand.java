package org.flab.deliveryplatform.shop.application.port.dto;

import lombok.Getter;
import org.flab.deliveryplatform.shop.domain.Option;

@Getter
public class OptionCommand {

    private String name;

    private int additionalPrice;

    public OptionCommand(String name, int additionalPrice) {
        this.name = name;
        this.additionalPrice = additionalPrice;
    }

    public Option toOption() {
        return Option.builder()
            .name(name)
            .additionalPrice(additionalPrice)
            .build();
    }
}
