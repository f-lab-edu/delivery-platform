package org.flab.deliveryplatform.shop.application.port.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import org.flab.deliveryplatform.shop.domain.OptionGroup;

@Getter
public class OptionGroupData {

    private Long id;

    private String name;

    private List<OptionData> options = new ArrayList<>();

    @Builder
    private OptionGroupData(Long id, String name, List<OptionData> options) {
        this.id = id;
        this.name = name;
        this.options = options;
    }

    public static OptionGroupData from(OptionGroup optionGroup) {
        List<OptionData> optionDataList = optionGroup.getOptions().stream()
            .map(OptionData::from)
            .collect(Collectors.toList());

        return OptionGroupData.builder()
            .id(optionGroup.getId())
            .name(optionGroup.getName())
            .options(optionDataList)
            .build();
    }
}
