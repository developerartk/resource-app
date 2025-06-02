package com.project.recourse.api.domain;

import com.project.recourse.api.domain.enums.CharacteristicType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "characteristic")
@EqualsAndHashCode
public class Characteristic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "resource_id")
    private Resource resource;

    @Size(max = 5, message = "Code max length - 5 characters")
    private String code;

    @Enumerated(EnumType.STRING)
    private CharacteristicType characteristicType;

    private String value;

    @Override
    public String toString() {
        return "Characteristic {" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", characteristicType=" + characteristicType +
                ", value='" + value + '\'' +
                '}';
    }
}
