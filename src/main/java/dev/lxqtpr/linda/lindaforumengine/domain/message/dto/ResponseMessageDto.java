package dev.lxqtpr.linda.lindaforumengine.domain.message.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ResponseMessageDto {
    private UUID id;
    private String text;
    private String author;
    private LocalDateTime created;
}
