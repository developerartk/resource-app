package com.project.recourse.api.web;

import com.project.recourse.api.service.ResourceService;
import com.project.recourse.api.service.ResourceServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notify")
@RequiredArgsConstructor
public class NotificationApiEndpoint {

    private final ResourceService resourceService;

    @PostMapping("/broadcast-all-resources")
    @Operation(summary = "Send all resources info")
    public ResponseEntity<Void> broadcastAllResources() {
        resourceService.sendAllResources();
        return ResponseEntity.accepted().build();
    }

}
