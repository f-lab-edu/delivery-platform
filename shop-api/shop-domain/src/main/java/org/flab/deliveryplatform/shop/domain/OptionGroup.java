package org.flab.deliveryplatform.shop.domain;

import java.util.HashSet;
import java.util.Set;
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

@EqualsAndHashCode(of = {"id"})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OptionGroup {

    @Column(name = "option_group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    @JoinColumn(name = "menu_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Menu menu;

    @OneToMany(mappedBy = "optionGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Option> options = new HashSet<>();

    @Builder
    private OptionGroup(Long id, String name, Menu menu, Set<Option> options) {
        this.id = id;
        this.name = name;
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
}
