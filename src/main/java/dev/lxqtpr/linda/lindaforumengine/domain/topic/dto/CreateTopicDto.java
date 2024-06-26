package dev.lxqtpr.linda.lindaforumengine.domain.topic.dto;

import dev.lxqtpr.linda.lindaforumengine.domain.message.dto.CreateMessageDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateTopicDto {
    @NotEmpty(message = "Topic name is mandatory")
    private String topicName;

    @NotNull
    private CreateMessageDto messageDto;
}
