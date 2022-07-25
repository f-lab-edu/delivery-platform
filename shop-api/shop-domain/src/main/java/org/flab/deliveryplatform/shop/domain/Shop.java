package org.flab.deliveryplatform.shop.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.deliveryplatform.shop.domain.exception.DuplicateOptionGroupNameException;
import org.flab.deliveryplatform.shop.domain.exception.MenuNotFoundException;
import org.flab.deliveryplatform.shop.domain.exception.OptionGroupNotFoundException;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Shop {

    @Column(name = "shop_id")
    @GeneratedValue
    @Id
    private Long id;

    private String name;

    @Embedded
    private PhoneNumber phoneNumber;

    @Embedded
    private Address address;

    @Enumerated(value = EnumType.STRING)
    private ShopStatus status;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private List<Menu> menus = new ArrayList<>();

    @Builder
    private Shop(Long id, String name, PhoneNumber phoneNumber, Address address, ShopStatus status, List<Menu> menus) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.status = status;
        this.menus = menus;
    }

    public void changeWith(Shop source) {
        this.name = source.name;
        this.phoneNumber = source.phoneNumber;
        this.address = source.address;
    }

    public void close() {
        this.status = ShopStatus.CLOSED;
    }

    public void addMenu(Menu menu) {
        this.menus.add(menu);
        menu.setShop(this);
    }

    public void addOptionGroup(Long menuId, OptionGroup optionGroup) {
        Menu menu = findMenu(menuId);

        menu.getOptionGroups().stream()
            .filter(og -> og.getName().equals(optionGroup.getName()))
            .findAny()
            .ifPresent(og -> {
                throw new DuplicateOptionGroupNameException(optionGroup.getName());
            });

        menu.addOptionGroup(optionGroup);
    }

    private Menu findMenu(Long menuId) {
        Menu menu = this.menus.stream()
            .filter(m -> m.getId() == menuId)
            .findAny()
            .orElseThrow(() -> new MenuNotFoundException(menuId));

        return menu;
    }

    public void addOption(Long menuId, Long optionGroupId, Option option) {
        Menu menu = findMenu(menuId);

        OptionGroup optionGroup = menu.getOptionGroups().stream()
            .filter(og -> og.getId() == optionGroupId)
            .findAny()
            .orElseThrow(() -> new OptionGroupNotFoundException(optionGroupId));

        optionGroup.addOption(option);
    }
}
