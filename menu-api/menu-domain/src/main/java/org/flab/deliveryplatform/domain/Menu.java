package org.flab.deliveryplatform.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Menu {

    @GeneratedValue
    @Id
    private Long id;

    private String name;

    @Embedded
    private ShopId shopId;

    @Builder
    private Menu(Long id, String name, ShopId shopId) {
        this.id = id;
        this.name = name;
        this.shopId = shopId;
    }
}
