package org.flab.deliveryplatform.shop.application.port.dto;

import lombok.Getter;
import org.flab.deliveryplatform.shop.domain.Option;

@Getter
public class OptionData {

    private Long id;

    private String name;

    private int additionalPrice;

    public OptionData(Long id, String name, int additionalPrice) {
        this.id = id;
        this.name = name;
        this.additionalPrice = additionalPrice;
    }

    public static OptionData from(Option option) {
        return new OptionData(option.getId(), option.getName(), option.getAdditionalPrice());
    }
}
