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
public class Option {

    @Column(name = "option_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    private int additionalPrice;

    @JoinColumn(name = "option_group_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private OptionGroup optionGroup;

    @Builder
    private Option(Long id, String name, int additionalPrice, OptionGroup optionGroup) {
        this.id = id;
        this.name = name;
        this.additionalPrice = additionalPrice;
        this.optionGroup = optionGroup;
    }

    public void setOptionGroup(OptionGroup optionGroup) {
        this.optionGroup = optionGroup;
    }
}
