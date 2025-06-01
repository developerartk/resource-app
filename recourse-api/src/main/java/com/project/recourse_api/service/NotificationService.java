package com.project.recourse_api.service;

import com.project.recourse_api.domain.Notification;
import com.project.recourse_api.domain.Resource;
import com.project.recourse_api.dto.ResourceDto;
import com.project.recourse_api.repository.NotificationRepository;
import com.project.recourse_api.template.NotificationTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final NotificationTemplate notificationTemplate;
    private final NotificationRepository notificationRepository;

    @Async
    public void sendResourceUpdatingNotification(ResourceDto resourceDto) {
        String topic = "resource-update";
        String message = notificationTemplate.buildResourceUpdateMessage(resourceDto);
        try {
            kafkaTemplate.send(topic, resourceDto.toString(), message);
            notificationRepository.save(createMessage(resourceDto, topic));
        } catch (Exception e) {
            log.error("Failed to send message.", e);
        }
    }

    @Async
    public void sendResourceCreationNotification(ResourceDto resourceDto) {
        String topic = "resource-create";
        String message = notificationTemplate.buildResourceCreationMessage(resourceDto);
        try {
            kafkaTemplate.send(topic, resourceDto.toString(), message);
            notificationRepository.save(createMessage(resourceDto, topic));
        } catch (Exception e) {
            log.error("Failed to send message.", e);
        }
    }

    public Notification createMessage(ResourceDto resourceDto, String topic) {
        return Notification.builder()
        .topic(topic)
        .key(resourceDto.toString())
        .text(notificationTemplate.buildResourceCreationMessage(resourceDto))
        .dateCreated(LocalDateTime.now())
        .sentSuccessfully(false)
        .build();
    }

}
