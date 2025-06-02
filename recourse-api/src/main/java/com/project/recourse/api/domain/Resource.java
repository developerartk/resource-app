package com.project.recourse.api.domain;

import com.project.recourse.api.domain.enums.ResourceType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "resource")
@EqualsAndHashCode
@Builder
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ResourceType type;
    private Integer countryCode;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Location location;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "resource")
    private List<Characteristic> characteristics;

    @Override
    public String toString() {
        return "Resource {" +
                "id=" + id +
                ", type=" + type +
                ", countryCode=" + countryCode +
                ", location=" + location +
                ", characteristics=" + characteristics +
                '}';
    }
}
