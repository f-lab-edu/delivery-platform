package org.flab.deliveryplatform.shop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OptionGroup {

    @Column(name = "menu_option_group_id")
    @GeneratedValue
    @Id
    private Long id;

    private String name;

    @JoinColumn(name = "menu_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Menu menu;

    @Builder
    private OptionGroup(Long id, String name, Menu menu) {
        this.id = id;
        this.name = name;
        this.menu = menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
