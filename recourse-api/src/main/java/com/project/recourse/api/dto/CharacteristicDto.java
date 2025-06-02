package com.project.recourse.api.dto;

import com.project.recourse.api.domain.enums.CharacteristicType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CharacteristicDto {

    private Long id;
    private String code;
    private CharacteristicType characteristicType;
    private String value;
    private Long resourceId;
}
