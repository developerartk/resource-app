package com.project.recourse_api.service;

import com.project.recourse_api.domain.Location;
import com.project.recourse_api.dto.LocationDto;
import com.project.recourse_api.dto.mapper.LocationDtoMapper;
import com.project.recourse_api.repository.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LocationService {

    private LocationDtoMapper locationDtoMapper;
    private LocationRepository locationRepository;

    public Location saveNewLocation(LocationDto locationDto) {
        return locationRepository.save(locationDtoMapper.mapDtoToLocation(locationDto));
    }
}
