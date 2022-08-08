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
import org.flab.deliveryplatform.shop.domain.exception.OptionNotFoundException;
import org.hibernate.annotations.SortNatural;

@EqualsAndHashCode(of = {"id"})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
public class OptionGroup implements Comparable<OptionGroup> {

    @Column(name = "option_group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    private int displayOrder;

    @JoinColumn(name = "menu_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Menu menu;

    @SortNatural
    @OneToMany(mappedBy = "optionGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private SortedSet<Option> options = new TreeSet<>();

    private OptionGroup(Long id, String name, int displayOrder, Menu menu, SortedSet<Option> options) {
        this.id = id;
        this.name = name;
        this.displayOrder = displayOrder;
        this.menu = menu;
        this.options = options;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void addOption(Option option) {
        this.options.add(option);
        option.setOptionGroup(this);
    }

    public void deleteOption(Long optionId) {
        Option option = findOption(optionId);
        this.options.remove(option);
    }

    private Option findOption(Long optionId) {
        return this.options.stream()
            .filter(o -> o.getId() == optionId)
            .findAny()
            .orElseThrow(() -> new OptionNotFoundException(optionId));
    }

    @Override
    public int compareTo(OptionGroup optionGroup) {
        return Integer.compare(this.displayOrder, optionGroup.getDisplayOrder());
    }
}
