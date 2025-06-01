package com.project.recourse_api.dto.mapper;

import com.project.recourse_api.domain.Characteristic;
import com.project.recourse_api.domain.enums.CharacteristicType;
import com.project.recourse_api.dto.CharacteristicDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
public class CharacteristicDtoMapper {

    public CharacteristicDto mapCharacteristicToDto (Characteristic characteristic) {
        return CharacteristicDto.builder()
                        .code(characteristic.getCode())
                        .characteristicType(characteristic.getCharacteristicType().name())
                        .value(characteristic.getValue())
                        .build();
        }
        
    public Characteristic mapDtoToCharacteristic (CharacteristicDto characteristicDto) {
        return Characteristic.builder()
                        .code(characteristicDto.getCode())
                        .characteristicType(CharacteristicType.valueOf(
                                characteristicDto.getCharacteristicType()))
                        .value(characteristicDto.getValue())
                        .build();
    }
        

}
