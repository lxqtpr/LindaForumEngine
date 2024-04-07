package dev.lxqtpr.linda.lindaforumengine.domain.message.dto;

import lombok.Data;


@Data
public class CreateMessageDto {
    private String text;
    private String authorUsername;
}
