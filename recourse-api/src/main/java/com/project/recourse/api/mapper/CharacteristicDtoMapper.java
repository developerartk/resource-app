package com.project.recourse.api.mapper;

import com.project.recourse.api.domain.Characteristic;
import com.project.recourse.api.domain.Resource;
import com.project.recourse.api.dto.CharacteristicDto;
import org.springframework.stereotype.Component;

@Component
public class CharacteristicDtoMapper {

    public CharacteristicDto mapCharacteristicToDto(Characteristic characteristic) {
        return CharacteristicDto.builder()
                .id(characteristic.getId())
                .code(characteristic.getCode())
                .characteristicType(characteristic.getCharacteristicType())
                .value(characteristic.getValue())
                .resourceId(characteristic.getResource().getId())
                .build();
    }

    public Characteristic mapDtoToCharacteristic(CharacteristicDto characteristicDto, Resource resource) {
        return Characteristic.builder()
                .code(characteristicDto.getCode())
                .characteristicType(characteristicDto.getCharacteristicType())
                .value(characteristicDto.getValue())
                .resource(resource)
                .build();
    }

    public Characteristic mapDtoToCharacteristic(Characteristic characteristic, CharacteristicDto characteristicDto) {
        characteristic.setId(characteristicDto.getId());
        characteristic.setCode(characteristicDto.getCode());
        characteristic.setCharacteristicType(characteristicDto.getCharacteristicType());
        characteristic.setValue(characteristicDto.getValue());
        return characteristic;
    }

}
