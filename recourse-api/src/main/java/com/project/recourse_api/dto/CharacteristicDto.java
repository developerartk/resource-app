package com.project.recourse_api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CharacteristicDto {

    private String code;
    private String characteristicType;
    private String value;
}
