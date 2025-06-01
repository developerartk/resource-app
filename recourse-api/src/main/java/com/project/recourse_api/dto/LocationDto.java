package com.project.recourse_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class LocationDto {

    private String streetAddress;
    private String city;
    private String postalCode;
    private Integer countryCode;
}
