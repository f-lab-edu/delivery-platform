package org.flab.deliveryplatform.menu.appliciation.port.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CreateMenuCommand {

    private String name;

    public CreateMenuCommand(String name) {
        this.name = name;
    }
}
