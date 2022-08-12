package org.flab.deliveryplatform.shop.application.port.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.deliveryplatform.shop.domain.OptionGroup;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CreateOptionGroupCommand {

    private String name;

    private int displayOrder;

    public CreateOptionGroupCommand(String name, int displayOrder) {
        this.name = name;
        this.displayOrder = displayOrder;
    }

    public OptionGroup toOptionGroup() {
        return OptionGroup.builder()
            .name(name)
            .displayOrder(displayOrder)
            .build();
    }
}
