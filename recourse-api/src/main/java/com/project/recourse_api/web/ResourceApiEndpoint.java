package com.project.recourse_api.web;

import com.project.recourse_api.dto.ResourceDto;
import com.project.recourse_api.service.NotificationService;
import com.project.recourse_api.service.ResourceService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/resource")
public class ResourceApiEndpoint {

    private ResourceService resourceService;
    private NotificationService notificationService;

    @GetMapping("/get/{id}")
    public ResponseEntity<ResourceDto> getResource(Long id) {
        return ResponseEntity.ok(resourceService.getResourceDto(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResourceDto> createNewResource(@RequestBody ResourceDto resourceDto) {
        ResourceDto resource = resourceService.createNewResource(resourceDto);
        notificationService.sendResourceCreationNotification(resource);
        return ResponseEntity.ok(resource);
    }

    @PutMapping("/update")
    public ResponseEntity<ResourceDto> updateResource(@RequestBody ResourceDto resourceDto) {
        ResourceDto resource = resourceService.updateResource(resourceDto);
        notificationService.sendResourceUpdatingNotification(resourceDto);
        return ResponseEntity.ok(resourceService.updateResource(resourceDto));
    }

    @PutMapping("/delete")
    public ResponseEntity<Void> deleteResource (@RequestBody ResourceDto resourceDto) {
        resourceService.deleteResource(resourceDto);
        return ResponseEntity.noContent().build();
    }


}
