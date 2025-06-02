package com.project.recourse.api.service;

import com.project.recourse.api.domain.Location;
import com.project.recourse.api.dto.LocationDto;
import com.project.recourse.api.mapper.LocationDtoMapper;
import com.project.recourse.api.repository.LocationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationDtoMapper locationDtoMapper;
    private final LocationRepository locationRepository;

    public Location saveLocation(LocationDto locationDto) {
        return locationRepository.save(locationDtoMapper.mapDtoToLocation(locationDto));
    }

    public Location updateLocation(LocationDto locationDto) {
        return locationRepository.findById(locationDto.getId())
                .map(location -> locationRepository.save(locationDtoMapper.mapDtoToLocation(location, locationDto)))
                .orElseThrow(() -> new EntityNotFoundException(String.format("Location with ID %d not found.", locationDto.getId())));
    }
}
