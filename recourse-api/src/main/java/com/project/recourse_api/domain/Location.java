package com.project.recourse_api.domain;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Location {

    private String streetAddress;
    private String city;
    private String postalCode;
    private Integer countryCode;
}
