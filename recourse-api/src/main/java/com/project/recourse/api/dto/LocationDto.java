package com.project.recourse.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationDto {

    private Long id;
    private String streetAddress;
    private String city;
    private String postalCode;
    private Integer countryCode;
}
