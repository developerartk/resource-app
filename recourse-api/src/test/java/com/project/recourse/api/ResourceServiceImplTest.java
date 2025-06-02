package com.project.recourse.api;

import com.project.recourse.api.domain.Characteristic;
import com.project.recourse.api.domain.Location;
import com.project.recourse.api.domain.Resource;
import com.project.recourse.api.domain.enums.CharacteristicType;
import com.project.recourse.api.domain.enums.ResourceType;
import com.project.recourse.api.dto.CharacteristicDto;
import com.project.recourse.api.dto.LocationDto;
import com.project.recourse.api.dto.ResourceDto;
import com.project.recourse.api.mapper.ResourceDtoMapper;
import com.project.recourse.api.repository.ResourceRepository;
import com.project.recourse.api.service.CharacteristicService;
import com.project.recourse.api.service.LocationService;
import com.project.recourse.api.service.NotificationService;
import com.project.recourse.api.service.ResourceServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ResourceServiceImplTest {

    @Mock
    private ResourceRepository resourceRepository;
    @Mock
    private ResourceDtoMapper resourceDtoMapper;
    @Mock
    private LocationService locationService;
    @Mock
    private CharacteristicService characteristicService;
    @Mock
    private NotificationService notificationService;
    @InjectMocks
    private ResourceServiceImpl resourceServiceImp;

    @Test
    void test_findResourceById() {
        Long resourceId = 1L;
        var resourceDto = ResourceDto.builder().id(resourceId).build();
        Resource resource = createTestResource(resourceId);
        when(resourceRepository.findById(resourceId)).thenReturn(Optional.of(resource));
        when(resourceDtoMapper.mapResourceToDto(resource)).thenReturn(resourceDto);
        assertDoesNotThrow(() -> resourceServiceImp.getResourceDto(resourceId));
        verify(resourceRepository, times(1)).findById(resourceId);
    }

    @Test
    void test_createResource() {
        var resourceDto =  createTesrResourceDto();
        var resource = createTestResource(1L);
        var characteristics = createTestCharacteristicsList();

        when(resourceRepository.save(resource)).thenReturn(resource);
        when(locationService.saveLocation(resourceDto.getLocationDto())).thenReturn(any());
        when(resourceDtoMapper.mapDtoToResource(resourceDto)).thenReturn(resource);
        when(characteristicService
                .saveCharacteristic(resourceDto.getCharacteristicDto(), resource)).thenReturn(characteristics);

        when(resourceDtoMapper.mapResourceToDto(resource)).thenReturn(resourceDto);
        assertDoesNotThrow(() -> resourceServiceImp.createResource(resourceDto));

        verify(notificationService,times(1)).sendResourceCreationNotification(resourceDto);
        verify(resourceRepository, times(1)).save(any());
    }

    @Test
    void test_updateResource() {
        var resourceDto =  createTesrResourceDto();
        var resource = createTestResource(1L);
        var characteristics = createTestCharacteristicsList();

        when(locationService.updateLocation(resourceDto.getLocationDto())).thenReturn(any());
        when(characteristicService
                .updateCharacteristic(resourceDto)).thenReturn(characteristics);
        when(resourceRepository.findById(resourceDto.getId())).thenReturn(Optional.of(resource));

        assertDoesNotThrow(() -> resourceServiceImp.updateResource(resourceDto));

        verify(resourceDtoMapper, times(1)).updateResourceFromDto(resource, resourceDto);
        verify(notificationService,times(1)).sendResourceUpdatingNotification(any());
    }

    private Resource createTestResource(Long id) {
        Resource resource = new Resource();
        resource.setId(id);
        resource.setCountryCode(555);
        resource.setLocation(createTestLocation());
        resource.setCharacteristics(createTestCharacteristicsList());
        resource.setCountryCode(1);
        return resource;
    }

    private ResourceDto createTesrResourceDto() {
        return ResourceDto.builder()
                .countryCode(555)
                .type(ResourceType.CONNECTION_POINT)
                .locationDto(createTestLocationDto())
                .characteristicDto(createTestCharacteristicsDtoList())
                .build();
    }

    private LocationDto createTestLocationDto() {
        return LocationDto.builder()
                .countryCode(888)
                .postalCode("888")
                .streetAddress("Pikk tn 88")
                .city("Tallinn")
                .build();
    }


    private Location createTestLocation () {
        Location location = new Location();
        location.setId(123L);
        location.setCity("Tallinn");
        location.setCountryCode(555);
        location.setPostalCode("00112");
        location.setStreetAddress("Main Street 123");

        return location;
    }

    private List<Characteristic> createTestCharacteristicsList(){
        return List.of(Characteristic.builder()
                .code("5555")
                .characteristicType(CharacteristicType.CONSUMPTION_TYPE)
                .value("777")
                .build());
    }

    private List<CharacteristicDto> createTestCharacteristicsDtoList(){
        return List.of(CharacteristicDto.builder()
                .code("5555")
                .characteristicType(CharacteristicType.CONSUMPTION_TYPE)
                .value("777")
                .resourceId(1L)
                .build());
    }


}
