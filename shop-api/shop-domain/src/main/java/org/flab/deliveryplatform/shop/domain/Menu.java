package org.flab.deliveryplatform.shop.domain;

import java.util.SortedSet;
import java.util.TreeSet;
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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.deliveryplatform.shop.domain.exception.OptionGroupNotFoundException;
import org.hibernate.annotations.SortNatural;

@EqualsAndHashCode(of = {"id"})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Menu implements Comparable {

    @Column(name = "menu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    private String description;

    private int displayOrder;

    @SortNatural
    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private SortedSet<OptionGroup> optionGroups = new TreeSet<>();

    @JoinColumn(name = "shop_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @Builder
    private Menu(Long id, String name, String description, int displayOrder,
        SortedSet<OptionGroup> optionGroups, Shop shop) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.displayOrder = displayOrder;
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

    public void deleteOptionGroup(Long optionGroupId) {
        OptionGroup optionGroup = findOptionGroup(optionGroupId);
        this.optionGroups.remove(optionGroup);
    }

    public void deleteOption(Long optionGroupId, Long optionId) {
        OptionGroup optionGroup = findOptionGroup(optionGroupId);
        optionGroup.deleteOption(optionId);
    }

    public OptionGroup findOptionGroup(Long optionGroupId) {
        return this.getOptionGroups().stream()
            .filter(og -> og.getId() == optionGroupId)
            .findAny()
            .orElseThrow(() -> new OptionGroupNotFoundException(optionGroupId));
    }

    @Override
    public int compareTo(Object o) {
        Menu menu = (Menu) o;
        return Integer.compare(this.displayOrder, menu.getDisplayOrder());
    }
}
