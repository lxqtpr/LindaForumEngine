package dev.lxqtpr.linda.lindaforumengine.domain.topic.dto;

import dev.lxqtpr.linda.lindaforumengine.domain.message.dto.CreateMessageDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateTopicDto {
    @NotNull
    private String topicName;

    @NotNull
    private CreateMessageDto messageDto;
}
