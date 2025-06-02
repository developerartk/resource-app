package com.project.recourse.api.service;

import com.project.recourse.api.dto.ResourceDto;

public interface ResourceService {

    ResourceDto getResourceDto(Long id);

    ResourceDto createResource(ResourceDto resourceDto);

    ResourceDto updateResource(ResourceDto resourceDto);

    void deleteResource(ResourceDto resourceDto);

    void sendAllResources();
}
