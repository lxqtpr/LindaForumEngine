package dev.lxqtpr.linda.lindaforumengine.domain.message.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UpdateMessageDto {

    @NotNull(message = "Id is mandatory")
    private UUID id;

    @NotNull(message = "Topic id is mandatory")
    private UUID topic_id;

    @NotBlank(message = "Text is mandatory")
    private String text;

    @NotBlank(message = "Author is mandatory")
    private String author;

    @NotNull(message = "Created is mandatory")
    private LocalDateTime created;
}
