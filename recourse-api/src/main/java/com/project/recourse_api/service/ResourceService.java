package com.project.recourse_api.service;

import com.project.recourse_api.exceptions.MissingInfoException;
import com.project.recourse_api.domain.Resource;
import com.project.recourse_api.dto.ResourceDto;
import com.project.recourse_api.dto.mapper.ResourceDtoMapper;
import com.project.recourse_api.repository.ResourceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ResourceService {
    //добавить log.info() в каждый метод
    //покрыть тестами каждый метод
    private ResourceDtoMapper resourceDtoMapper;
    private ResourceRepository resourceRepository;
    private LocationService locationService;
    private CharacteristicService characteristicService;

    public ResourceDto createNewResource(ResourceDto resourceDto) {
        return saveResource(new Resource(), resourceDto);
    }

    public ResourceDto updateResource(ResourceDto resourceDto) {
       return saveResource(findResource(resourceDto.getId()), resourceDto);
    }

    @Transactional
    public ResourceDto saveResource(Resource resource, ResourceDto resourceDto) {

        resource.setType(resourceDto.getType());
        resource.setCountryCode(resourceDto.getCountryCode());
        resource.setLocation(Optional.ofNullable(resourceDto.getLocationDto())
                        .map(locationService::saveNewLocation)
                        .orElseThrow(() -> new MissingInfoException("Missing Location info")));
        resource.setCharacteristics(resourceDto.getCharacteristicDtos() != null ?
                resourceDto.getCharacteristicDtos().stream()
                        .map(characteristic ->  characteristicService.saveNewCharacteristic(characteristic))
                        .collect(Collectors.toList()) : null);

        resource = resourceRepository.save(resource);
            return resourceDtoMapper.mapResourceToDto(resource);
    }

    public Resource findResource(Long resourceId){
        return resourceRepository.findById(resourceId)
                .orElseThrow(() -> new EntityNotFoundException("Resource with ID {} not found." + resourceId));
    }

    public List<ResourceDto> getAllResources() {
        return resourceRepository.findAll()
                .stream()
                .map(resourceDtoMapper::mapResourceToDto)
                .toList();
    }
    public ResourceDto getResourceDto (Long resourceId) {
        return resourceDtoMapper.mapResourceToDto(findResource(resourceId));
    }

    public void deleteResource(ResourceDto resourceDto) {
        resourceRepository.delete(findResource(resourceDto.getId()));
    }

}
