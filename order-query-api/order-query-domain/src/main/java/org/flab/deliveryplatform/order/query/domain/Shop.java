package org.flab.deliveryplatform.order.query.domain;

import lombok.Getter;

@Getter
public class Shop {

    private String name;

    public Shop(String name) {
        this.name = name;
    }
}
