package com.project.recourse.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.recourse.api.dto.ResourceDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value("${notification.topic-name.create}")
    private String topicCreateName;

    @Value("${notification.topic-name.update}")
    private String topicUpdateName;

    @Async
    public void sendResourceUpdatingNotification(ResourceDto resourceDto) {
        sendResource(topicUpdateName, resourceDto);
    }

    @Async
    public void sendResourceCreationNotification(ResourceDto resourceDto) {
        sendResource(topicCreateName, resourceDto);
    }

    @Async
    public void broadcastAllResources(List<ResourceDto> resourceDtoList) {
        sendResource(topicUpdateName, resourceDtoList);
    }

    public void sendResource(String topicName, Object payload) {
        try {
            String message = objectMapper.writeValueAsString(payload);
            kafkaTemplate.send(topicName, message);
        } catch (Exception e) {
            log.error("Failed to send message.", e);
        }
    }
}
