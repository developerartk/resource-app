package com.project.recourse.api.web;

import com.project.recourse.api.dto.ResourceDto;
import com.project.recourse.api.service.ResourceService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
@RequiredArgsConstructor
public class ResourceApiEndpoint {

    private final ResourceService resourceService;

    @GetMapping("/get/{id}")
    @Operation(summary = "Get all resources")
    public ResponseEntity<ResourceDto> getResource(@PathVariable Long id) {
        return ResponseEntity.ok(resourceService.getResourceDto(id));
    }

    @PostMapping("/create")
    @Operation(summary = "Create new resource")
    public ResponseEntity<ResourceDto> createNewResource(@RequestBody ResourceDto resourceDto) {
        return ResponseEntity.ok(resourceService.createResource(resourceDto));
    }

    @PutMapping("/update")
    @Operation(summary = "Update resource")
    public ResponseEntity<ResourceDto> updateResource(@RequestBody ResourceDto resourceDto) {
        return ResponseEntity.ok(resourceService.updateResource(resourceDto));
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Delete resource")
    public ResponseEntity<Void> deleteResource(@RequestBody ResourceDto resourceDto) {
        resourceService.deleteResource(resourceDto);
        return ResponseEntity.noContent().build();
    }

}
