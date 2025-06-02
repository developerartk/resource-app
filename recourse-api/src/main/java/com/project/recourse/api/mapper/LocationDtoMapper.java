package com.project.recourse.api.mapper;

import com.project.recourse.api.domain.Location;
import com.project.recourse.api.dto.LocationDto;
import org.springframework.stereotype.Component;

@Component
public class LocationDtoMapper {

    public LocationDto mapLocationToDto(Location location) {
        return LocationDto.builder()
                .id(location.getId())
                .streetAddress(location.getStreetAddress())
                .city(location.getCity())
                .postalCode(location.getPostalCode())
                .countryCode(location.getCountryCode())
                .build();
    }

    public Location mapDtoToLocation(LocationDto dto) {
        return Location.builder()
                .streetAddress(dto.getStreetAddress())
                .city(dto.getCity())
                .postalCode(dto.getPostalCode())
                .countryCode(dto.getCountryCode())
                .build();
    }

    public Location mapDtoToLocation(Location location, LocationDto locationDto) {
        location.setId(locationDto.getId());
        location.setStreetAddress(locationDto.getStreetAddress());
        location.setCity(locationDto.getCity());
        location.setPostalCode(locationDto.getPostalCode());
        location.setCountryCode(locationDto.getCountryCode());
        return location;
    }
}
