package org.flab.deliveryplatform.shop.application.port.dto;

import lombok.Getter;
import org.flab.deliveryplatform.shop.domain.Option;

@Getter
public class OptionData {

    private Long id;

    private String name;

    private int price;

    public OptionData(Long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public static OptionData from(Option option) {
        return new OptionData(option.getId(), option.getName(), option.getPrice());
    }
}
