package com.project.recourse_api.domain;

import com.project.recourse_api.domain.enums.CharacteristicType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Characteristic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Size(max = 5, message = "Code max length - 5 characters")
    private String code;

    @Enumerated(EnumType.STRING)

    private CharacteristicType characteristicType;

    private String value;
}
