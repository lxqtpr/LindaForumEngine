package dev.lxqtpr.linda.lindaforumengine.domain.topic;

import dev.lxqtpr.linda.lindaforumengine.domain.topic.dto.CreateTopicDto;
import dev.lxqtpr.linda.lindaforumengine.domain.topic.dto.ResponseTopicDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/topic")
@RestController
@RequiredArgsConstructor
public class TopicController {
    private final TopicService topicService;

    @GetMapping
    public List<ResponseTopicDto> findAllTopics(){
        return topicService.findAllTopics();
    }
    @GetMapping("/{topicId}")
    public ResponseTopicDto findTopicById(@PathVariable UUID topicId){
        return topicService.findTopicById(topicId);
    }
    @PostMapping
    public ResponseTopicDto createTopic(@RequestBody @Valid CreateTopicDto createTopicDto){
        return topicService.createTopic(createTopicDto);
    }
}
