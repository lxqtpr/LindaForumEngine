package dev.lxqtpr.linda.lindaforumengine.domain.topic;

import dev.lxqtpr.linda.lindaforumengine.core.exceptions.ResourceNotFoundException;
import dev.lxqtpr.linda.lindaforumengine.domain.message.MessageEntity;
import dev.lxqtpr.linda.lindaforumengine.domain.topic.dto.CreateTopicDto;
import dev.lxqtpr.linda.lindaforumengine.domain.topic.dto.ResponseTopicDto;
import dev.lxqtpr.linda.lindaforumengine.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;

import org.apache.logging.log4j.message.Message;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final ModelMapper modelMapper;
    private final TopicRepository topicRepository;
    private final UserRepository userRepository;

    public List<ResponseTopicDto> findAllTopics() {
        return topicRepository
                .findAll()
                .stream()
                .map(e -> modelMapper.map(e, ResponseTopicDto.class))
                .toList();
    }

    public ResponseTopicDto findTopicById(UUID topicId) {
        var topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new ResourceNotFoundException("Topic with this id does not exist"));
        return modelMapper.map(topic, ResponseTopicDto.class);
    }

    public ResponseTopicDto createTopic(CreateTopicDto createTopicDto) {
        var topicToSave = modelMapper.map(createTopicDto,TopicEntity.class );
        var messageToSave = modelMapper.map(createTopicDto.getMessageDto(), MessageEntity.class );
        var author = userRepository.findByUsername(createTopicDto.getMessageDto().getAuthorUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User with this username does not exist"));
        messageToSave.setAuthor(author);
        topicToSave.addMessage(messageToSave);
        return modelMapper.map(topicRepository.save(topicToSave), ResponseTopicDto.class);
    }
}
