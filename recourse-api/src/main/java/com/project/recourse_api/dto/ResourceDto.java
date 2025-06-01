package com.project.recourse_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ResourceDto {

    private Long id;
    private String type;
    private Integer countryCode;
    private LocationDto locationDto;
    private List<CharacteristicDto> characteristicDtos;


}
