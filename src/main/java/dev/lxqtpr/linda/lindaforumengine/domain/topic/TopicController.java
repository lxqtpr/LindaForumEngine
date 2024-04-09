package dev.lxqtpr.linda.lindaforumengine.domain.topic;

import dev.lxqtpr.linda.lindaforumengine.domain.message.dto.CreateMessageDto;
import dev.lxqtpr.linda.lindaforumengine.domain.message.dto.ResponseMessageDto;
import dev.lxqtpr.linda.lindaforumengine.domain.message.dto.UpdateMessageDto;
import dev.lxqtpr.linda.lindaforumengine.domain.topic.dto.CreateTopicDto;
import dev.lxqtpr.linda.lindaforumengine.domain.topic.dto.ResponseTopicDto;
import dev.lxqtpr.linda.lindaforumengine.domain.topic.dto.UpdateTopicDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/topic")
@RestController
@RequiredArgsConstructor
public class TopicController {
    private final TopicService topicService;

    @GetMapping
    public Page<ResponseTopicDto> findAllTopics(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit
    ){
        return topicService.findAllTopics(offset, limit);
    }
    @GetMapping("/{topicId}")
    public Page<ResponseMessageDto> getTopicMessages(
            @PathVariable UUID topicId,
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit
    ){
        return topicService.getTopicMessages(topicId, limit, offset);
    }
    @PostMapping
    public ResponseTopicDto createTopic(@RequestBody @Valid CreateTopicDto createTopicDto){
        return topicService.createTopic(createTopicDto);
    }
    @PostMapping("/{topicId}/message")
    public ResponseTopicDto createMessage(@RequestBody @Valid CreateMessageDto createMessageDto, @PathVariable UUID topicId){
        return topicService.createMessage(topicId, createMessageDto);
    }
    @PutMapping
    public ResponseTopicDto updateTopic(@RequestBody @Valid UpdateTopicDto updateTopicDto){
        return topicService.updateTopic(updateTopicDto);
    }

    @PreAuthorize("@securityExpression.canAccessUserToMessage(#updateMessageDto.id)")
    @PutMapping("/message")
    public ResponseTopicDto updateMessage(@RequestBody @Valid UpdateMessageDto updateMessageDto){
        return topicService.updateMessage(updateMessageDto);
    }

    @DeleteMapping("/{topicId}")
    public void deleteTopic(@PathVariable UUID topicId){
        topicService.deleteTopic(topicId);
    }
}
