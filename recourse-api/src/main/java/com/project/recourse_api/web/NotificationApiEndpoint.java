package com.project.recourse_api.web;

import com.project.recourse_api.dto.ResourceDto;
import com.project.recourse_api.service.NotificationService;
import com.project.recourse_api.service.ResourceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notify")
@AllArgsConstructor
public class NotificationApiEndpoint {

    private final ResourceService resourceService;
    private final NotificationService notificationService;

    @PostMapping("/broadcast-all-resources")
    public ResponseEntity<Void> broadcastAllResources() {
        List<ResourceDto> resources = resourceService.getAllResources();
        resources.forEach(notificationService::sendResourceCreationNotification);

        return ResponseEntity.accepted().build();
    }

}
