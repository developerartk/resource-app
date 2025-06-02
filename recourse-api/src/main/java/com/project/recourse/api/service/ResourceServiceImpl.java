package com.project.recourse.api.service;

import com.project.recourse.api.dto.ResourceDto;
import com.project.recourse.api.mapper.ResourceDtoMapper;
import com.project.recourse.api.repository.ResourceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final ResourceDtoMapper resourceDtoMapper;
    private final ResourceRepository resourceRepository;
    private final LocationService locationService;
    private final CharacteristicService characteristicService;
    private final NotificationService notificationService;

    @Override
    @Transactional
    public ResourceDto createResource(ResourceDto resourceDto) {
        var location = locationService.saveLocation(resourceDto.getLocationDto());
        var resource = resourceDtoMapper.mapDtoToResource(resourceDto);
        resource.setLocation(location);

        var savedResource = resourceRepository.save(resource);
        resourceDto.setId(savedResource.getId());
        var characteristics = characteristicService
                .saveCharacteristic(resourceDto.getCharacteristicDto(), savedResource);
        savedResource.setCharacteristics(characteristics);

        characteristics.forEach(characteristic -> characteristic.setResource(savedResource));
        var resourceDtoSaved = resourceDtoMapper.mapResourceToDto(savedResource);
        notificationService.sendResourceCreationNotification(resourceDtoSaved);
        return resourceDtoSaved;
    }

    @Override
    @Transactional
    public ResourceDto updateResource(ResourceDto resourceDto) {
        var location = locationService.updateLocation(resourceDto.getLocationDto());
        var characteristics = characteristicService.updateCharacteristic(resourceDto);
        var resource = resourceRepository.findById(resourceDto.getId())
                .orElseThrow(() -> new EntityNotFoundException(String.format("Resource with ID %d not found.", resourceDto.getId())));
        resource.setLocation(location);
        resource.setCharacteristics(characteristics);
        resourceDtoMapper.updateResourceFromDto(resource, resourceDto);
        var resourceDtoUpdated = resourceDtoMapper.mapResourceToDto(resource);
        notificationService.sendResourceUpdatingNotification(resourceDtoUpdated);
        return resourceDtoUpdated;
    }

    @Override
    public ResourceDto getResourceDto(Long resourceId) {
        return resourceDtoMapper.mapResourceToDto(resourceRepository.findById(resourceId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Resource with ID %d not found.", resourceId))));
    }

    @Override
    @Transactional
    public void deleteResource(ResourceDto resourceDto) {
        resourceRepository.deleteById(resourceDto.getId());
    }

    @Override
    public void sendAllResources() {
        notificationService.broadcastAllResources(resourceRepository.findAll().stream()
                .map(resourceDtoMapper::mapResourceToDto)
                .toList());
    }
}
