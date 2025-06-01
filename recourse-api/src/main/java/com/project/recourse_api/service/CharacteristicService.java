package com.project.recourse_api.service;

import com.project.recourse_api.domain.Characteristic;
import com.project.recourse_api.dto.CharacteristicDto;
import com.project.recourse_api.dto.mapper.CharacteristicDtoMapper;
import com.project.recourse_api.repository.CharacteristicRepository;


public class CharacteristicService {

    private CharacteristicRepository characteristicRepository;
    private CharacteristicDtoMapper characteristicDtoMapper;

    public Characteristic saveNewCharacteristic(CharacteristicDto characteristicDto) {
        return characteristicDtoMapper.mapDtoToCharacteristic(characteristicDto);
    }
}
