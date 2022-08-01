package org.flab.deliveryplatform.shop.application.port.dto;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import org.flab.deliveryplatform.shop.domain.Menu;

@Getter
public class MenuData {

    private Long id;

    private int price;

    private List<OptionGroupData> optionGroups;

    @Builder
    private MenuData(Long id, int price, List<OptionGroupData> optionGroups) {
        this.id = id;
        this.price = price;
        this.optionGroups = optionGroups;
    }

    public static MenuData from(Menu menu) {
        List<OptionGroupData> optionGroupDataList = menu.getOptionGroups().stream()
            .map(OptionGroupData::from)
            .collect(Collectors.toList());

        return MenuData.builder()
            .id(menu.getId())
            .price(menu.getPrice())
            .optionGroups(optionGroupDataList)
            .build();
    }
}
