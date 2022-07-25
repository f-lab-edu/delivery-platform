package org.flab.deliveryplatform.shop.application.port.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.deliveryplatform.shop.domain.OptionGroup;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CreateOptionGroupCommand {

    private String name;

    public CreateOptionGroupCommand(String name) {
        this.name = name;
    }

    public OptionGroup toOptionGroup() {
        return OptionGroup.builder()
            .name(name)
            .build();
    }
}
