package com.project.recourse_api.repository;

import com.project.recourse_api.domain.Characteristic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacteristicRepository extends JpaRepository<Characteristic, Long> {
}
