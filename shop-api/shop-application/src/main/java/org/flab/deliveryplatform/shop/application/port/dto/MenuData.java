package org.flab.deliveryplatform.shop.application.port.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import org.flab.deliveryplatform.shop.domain.Menu;

@Getter
public class MenuData {

    private Long id;

    private String name;

    private List<OptionGroupData> optionGroups = new ArrayList<>();

    @Builder
    private MenuData(Long id, String name, List<OptionGroupData> optionGroups) {
        this.id = id;
        this.name = name;
        this.optionGroups = optionGroups;
    }

    public static MenuData from(Menu menu) {
        List<OptionGroupData> optionGroupDataList = menu.getOptionGroups().stream()
            .map(OptionGroupData::from)
            .collect(Collectors.toList());

        return MenuData.builder()
            .id(menu.getId())
            .name(menu.getName())
            .optionGroups(optionGroupDataList)
            .build();
    }
}
