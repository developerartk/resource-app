package com.project.recourse_api.repository;

import com.project.recourse_api.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
