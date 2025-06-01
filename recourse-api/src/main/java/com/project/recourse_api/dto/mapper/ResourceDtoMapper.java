package com.project.recourse_api.dto.mapper;

import com.project.recourse_api.domain.Resource;
import com.project.recourse_api.dto.ResourceDto;

import java.util.stream.Collectors;

public class ResourceDtoMapper {

    private LocationDtoMapper locationDtoMapper;
    private CharacteristicDtoMapper characteristicDtoMapper;

    public ResourceDto mapResourceToDto(Resource resource) {
        return ResourceDto.builder()
                .id(resource.getId())
                .type(resource.getType())
                .countryCode(resource.getCountryCode())
                .locationDto(resource.getLocation() != null ? locationDtoMapper.mapLocationToDto(resource.getLocation()) : null)
                .characteristicDtos(resource.getCharacteristics() != null ? resource.getCharacteristics().stream()
                        .map(characteristic ->  characteristicDtoMapper.mapCharacteristicToDto(characteristic))
                        .collect(Collectors.toList()) : null)
                .build();
    }

    public Resource mapDtoToResource(Resource resource, ResourceDto resourceDto) {
        resource.setId(resourceDto.getId());
        resource.setType(resourceDto.getType());
        resource.setCountryCode(resourceDto.getCountryCode());
        resource.setLocation(resourceDto.getLocationDto() != null ? locationDtoMapper.mapDtoToLocation(resourceDto.getLocationDto()) : null);
        resource.setCharacteristics(resourceDto.getCharacteristicDtos() != null ?
                resourceDto.getCharacteristicDtos().stream()
                        .map(characteristic ->  characteristicDtoMapper.mapDtoToCharacteristic(characteristic))
                        .collect(Collectors.toList()) : null);
        return resource;
    }

}
