package com.project.recourse.api.dto;

import com.project.recourse.api.domain.enums.ResourceType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResourceDto {

    private Long id;
    private ResourceType type;
    private Integer countryCode;
    private LocationDto locationDto;
    private List<CharacteristicDto> characteristicDto;

}
