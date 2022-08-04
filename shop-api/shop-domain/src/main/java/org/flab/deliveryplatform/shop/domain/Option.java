package org.flab.deliveryplatform.shop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "options")
@Entity
public class Option implements Comparable {

    @Column(name = "option_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    private int price;

    private int displayOrder;

    @JoinColumn(name = "option_group_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private OptionGroup optionGroup;

    @Builder
    public Option(Long id, String name, int price, int displayOrder, OptionGroup optionGroup) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.displayOrder = displayOrder;
        this.optionGroup = optionGroup;
    }

    public void setOptionGroup(OptionGroup optionGroup) {
        this.optionGroup = optionGroup;
    }

    @Override
    public int compareTo(Object o) {
        Option option = (Option) o;
        return Integer.compare(this.displayOrder, option.getDisplayOrder());
    }
}
