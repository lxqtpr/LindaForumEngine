package dev.lxqtpr.linda.lindaforumengine.domain.topic.dto;

import dev.lxqtpr.linda.lindaforumengine.domain.message.dto.ResponseMessageDto;
import dev.lxqtpr.linda.lindaforumengine.domain.topic.TopicEntity;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class ResponseTopicDto{
    private UUID id;
    private String name;
    private LocalDateTime created;
    private List<ResponseMessageDto> messages;
}
