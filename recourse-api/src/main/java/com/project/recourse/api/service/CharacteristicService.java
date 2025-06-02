package com.project.recourse.api.service;

import com.project.recourse.api.domain.Characteristic;
import com.project.recourse.api.domain.Resource;
import com.project.recourse.api.dto.CharacteristicDto;
import com.project.recourse.api.dto.ResourceDto;
import com.project.recourse.api.mapper.CharacteristicDtoMapper;
import com.project.recourse.api.repository.CharacteristicRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CharacteristicService {

    private final CharacteristicRepository characteristicRepository;
    private final CharacteristicDtoMapper characteristicDtoMapper;

    public List<Characteristic> saveCharacteristic(List<CharacteristicDto> characteristicDtos, Resource resource) {
        return characteristicDtos.stream()
                .map(characteristicDto ->
                        characteristicRepository.save(characteristicDtoMapper.mapDtoToCharacteristic(characteristicDto, resource)))
                .toList();
    }

    public List<Characteristic> updateCharacteristic(ResourceDto resourceDto) {
        return resourceDto.getCharacteristicDto().stream()
                .map(characteristicDto -> characteristicRepository.findById(characteristicDto.getId())
                        .map(characteristic ->
                                characteristicDtoMapper.mapDtoToCharacteristic(characteristic, characteristicDto))
                        .map(characteristicRepository::save)
                        .orElseThrow(() -> new EntityNotFoundException(String.format("Related characteristic with ID %d not found.", characteristicDto.getId()))))
                .toList();
    }
}
