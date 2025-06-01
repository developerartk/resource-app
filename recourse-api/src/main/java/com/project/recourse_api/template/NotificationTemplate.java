package com.project.recourse_api.template;

import com.project.recourse_api.domain.Resource;
import com.project.recourse_api.dto.ResourceDto;
import org.springframework.stereotype.Component;

@Component
public class NotificationTemplate {

    public String buildResourceCreationMessage (ResourceDto resourceDto) {
        return String.format(
                "New resource was created: %s",
                resourceDto.toString());
    }

    public String buildResourceUpdateMessage (ResourceDto resourceDto) {
        return String.format(
                "Resourse %d was updated. " +
                        "\n New data: %s", resourceDto.getId(),
                resourceDto.toString());
    }
}
