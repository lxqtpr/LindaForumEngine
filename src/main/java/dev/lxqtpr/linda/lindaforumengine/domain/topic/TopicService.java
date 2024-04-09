package dev.lxqtpr.linda.lindaforumengine.domain.topic;

import dev.lxqtpr.linda.lindaforumengine.core.exceptions.ResourceNotFoundException;
import dev.lxqtpr.linda.lindaforumengine.domain.message.MessageEntity;
import dev.lxqtpr.linda.lindaforumengine.domain.message.MessageRepository;
import dev.lxqtpr.linda.lindaforumengine.domain.message.dto.CreateMessageDto;
import dev.lxqtpr.linda.lindaforumengine.domain.message.dto.ResponseMessageDto;
import dev.lxqtpr.linda.lindaforumengine.domain.message.dto.UpdateMessageDto;
import dev.lxqtpr.linda.lindaforumengine.domain.topic.dto.CreateTopicDto;
import dev.lxqtpr.linda.lindaforumengine.domain.topic.dto.ResponseTopicDto;
import dev.lxqtpr.linda.lindaforumengine.domain.topic.dto.UpdateTopicDto;
import dev.lxqtpr.linda.lindaforumengine.domain.user.UserRepository;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final ModelMapper modelMapper;
    private final TopicRepository topicRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public Page<ResponseTopicDto> findAllTopics(@Min(0) Integer offset, @Min(1) @Max(100) Integer limit) {
        return topicRepository
                .findAll(PageRequest.of(offset, limit))
                .map(topicEntity -> modelMapper.map(topicEntity, ResponseTopicDto.class));
    }

    public Page<ResponseMessageDto> getTopicMessages(UUID topicId, @Min(1) @Max(100) Integer limit, @Min(0) Integer offset) {
        return messageRepository
                .findAllByTopicId(topicId, PageRequest.of(offset, limit))
                .map(message -> modelMapper.map(message, ResponseMessageDto.class));
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

    public ResponseTopicDto updateTopic(UpdateTopicDto updateTopicDto){
        var topicToUpdate = modelMapper.map(updateTopicDto,TopicEntity.class );
        return modelMapper.map(topicRepository.save(topicToUpdate), ResponseTopicDto.class);
    }
    public void deleteTopic(UUID id){
        topicRepository.deleteById(id);
    }

    public ResponseTopicDto createMessage(UUID topicId, CreateMessageDto createMessageDto) {
        var topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new ResourceNotFoundException("Topic with this id does not exist"));
        var messageToSave = modelMapper.map(createMessageDto, MessageEntity.class);
        var author = userRepository.findByUsername(createMessageDto.getAuthorUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User with this username does not exist"));
        messageToSave.setAuthor(author);
        topic.addMessage(messageToSave);
        return modelMapper.map(topicRepository.save(topic), ResponseTopicDto.class);
     }

    public ResponseTopicDto updateMessage(UpdateMessageDto updateMessageDto) {
        var messageToSave = modelMapper.map(updateMessageDto, MessageEntity.class);
        var author = userRepository.findByUsername(updateMessageDto.getAuthor())
                .orElseThrow(() -> new ResourceNotFoundException("User with this username does not exist"));
        var topic = topicRepository.findById(updateMessageDto.getTopic_id())
                .orElseThrow(() -> new ResourceNotFoundException("Topic with this id does not exist"));
        messageToSave.setAuthor(author);
        messageToSave.setTopic(topic);
        messageRepository.save(messageToSave);
        return modelMapper.map(topic, ResponseTopicDto.class);
    }
}
