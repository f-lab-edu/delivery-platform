package org.flab.deliveryplatform.shop.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Menu {

    @Column(name = "menu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    private int price;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<OptionGroup> optionGroups;

    @JoinColumn(name = "shop_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @Builder
    private Menu(Long id, String name, int price, List<OptionGroup> optionGroups, Shop shop) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.optionGroups = optionGroups;
        this.shop = shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public void addOptionGroup(OptionGroup optionGroup) {
        this.optionGroups.add(optionGroup);
        optionGroup.setMenu(this);
    }
}
