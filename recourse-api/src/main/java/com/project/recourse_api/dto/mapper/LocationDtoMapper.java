package com.project.recourse_api.dto.mapper;

import com.project.recourse_api.domain.Location;
import com.project.recourse_api.dto.LocationDto;

public class LocationDtoMapper {

    public LocationDto mapLocationToDto(Location location) {
        return LocationDto.builder()
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
}
