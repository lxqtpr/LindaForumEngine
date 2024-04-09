package dev.lxqtpr.linda.lindaforumengine.domain.topic.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UpdateTopicDto {

    @NotNull(message = "Id is mandatory")
    private UUID id;

    @NotEmpty(message = "Topic name is mandatory")
    private String name;

    private LocalDateTime created;
}
