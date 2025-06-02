package com.project.recourse.api.mapper;

import com.project.recourse.api.domain.Resource;
import com.project.recourse.api.dto.ResourceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ResourceDtoMapper {

    private final LocationDtoMapper locationDtoMapper;
    private final CharacteristicDtoMapper characteristicDtoMapper;

    public ResourceDto mapResourceToDto(Resource resource) {
        return ResourceDto.builder()
                .id(resource.getId())
                .type(resource.getType())
                .countryCode(resource.getCountryCode())
                .locationDto(resource.getLocation() != null ? locationDtoMapper.mapLocationToDto(resource.getLocation()) : null)
                .characteristicDto(resource.getCharacteristics() != null ? resource.getCharacteristics().stream()
                        .map(characteristicDtoMapper::mapCharacteristicToDto)
                        .collect(Collectors.toList()) : null)
                .build();
    }

    public Resource mapDtoToResource(ResourceDto resourceDto) {
        return Resource.builder()
                .type(resourceDto.getType())
                .countryCode(resourceDto.getCountryCode())
                .build();
    }

    public void updateResourceFromDto(Resource resource, ResourceDto resourceDto) {
        resource.setType(resourceDto.getType());
        resource.setCountryCode(resource.getCountryCode());
    }

}
