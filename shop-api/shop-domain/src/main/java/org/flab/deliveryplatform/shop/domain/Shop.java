package org.flab.deliveryplatform.shop.domain;

import java.util.SortedSet;
import java.util.TreeSet;
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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.deliveryplatform.shop.domain.exception.DuplicatedOptionGroupNameException;
import org.flab.deliveryplatform.shop.domain.exception.MenuNotFoundException;
import org.flab.deliveryplatform.shop.domain.exception.OptionGroupNotFoundException;
import org.hibernate.annotations.SortNatural;

@EqualsAndHashCode(of = {"id"})
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

    private int minOrderPrice;

    @SortNatural
    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, orphanRemoval = true)
    private SortedSet<Menu> menus = new TreeSet<>();

    @Builder
    private Shop(Long id, String name, PhoneNumber phoneNumber, Address address,
        ShopStatus status, int minOrderPrice, SortedSet<Menu> menus) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.status = status;
        this.minOrderPrice = minOrderPrice;
        this.menus = menus;
    }

    public void changeWith(Shop source) {
        this.name = source.name;
        this.phoneNumber = source.phoneNumber;
        this.address = source.address;
        this.minOrderPrice = source.minOrderPrice;
    }

    public void close() {
        this.status = ShopStatus.CLOSED;
    }

    public void addMenu(Menu menu) {
        this.menus.add(menu);
        menu.setShop(this);
    }

    public void deleteMenu(Long menuId) {
        Menu menu = findMenu(menuId);
        this.menus.remove(menu);
    }

    public void addOptionGroup(Long menuId, OptionGroup optionGroup) {
        Menu menu = findMenu(menuId);

        menu.getOptionGroups().stream()
            .filter(og -> og.getName().equals(optionGroup.getName()))
            .findAny()
            .ifPresent(og -> {
                throw new DuplicatedOptionGroupNameException(optionGroup.getName());
            });

        menu.addOptionGroup(optionGroup);
    }

    public void deleteOptionGroup(Long menuId, Long optionGroupId) {
        Menu menu = findMenu(menuId);
        menu.deleteOptionGroup(optionGroupId);
    }

    public void addOption(Long menuId, Long optionGroupId, Option option) {
        Menu menu = findMenu(menuId);

        OptionGroup optionGroup = menu.getOptionGroups().stream()
            .filter(og -> og.getId() == optionGroupId)
            .findAny()
            .orElseThrow(() -> new OptionGroupNotFoundException(optionGroupId));

        optionGroup.addOption(option);
    }

    public void deleteOption(Long menuId, Long optionGroupId, Long optionId) {
        Menu menu = findMenu(menuId);
        menu.deleteOption(optionGroupId, optionId);
    }

    private Menu findMenu(Long menuId) {
        Menu menu = this.menus.stream()
            .filter(m -> m.getId() == menuId)
            .findAny()
            .orElseThrow(() -> new MenuNotFoundException(menuId));

        return menu;
    }
}
