package org.flab.deliveryplatform.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class ShopId {

    @Column(name = "shop_id")
    private Long id;

    public ShopId(Long id) {
        this.id = id;
    }
}
