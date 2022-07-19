package org.flab.deliveryplatform.menu.appliciation.port.dto;

import lombok.Getter;
import org.flab.deliveryplatform.domain.Menu;

@Getter
public class MenuData {

    private String name;

    public MenuData(String name) {
        this.name = name;
    }

    public static MenuData from(Menu menu) {
        return new MenuData(menu.getName());
    }
}
