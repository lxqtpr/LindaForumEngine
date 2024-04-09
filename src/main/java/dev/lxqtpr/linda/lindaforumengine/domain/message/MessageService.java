package dev.lxqtpr.linda.lindaforumengine.domain.message;

import dev.lxqtpr.linda.lindaforumengine.domain.message.dto.CreateMessageDto;
import dev.lxqtpr.linda.lindaforumengine.domain.message.dto.ResponseMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public void deleteMessage(UUID messageId) {
        messageRepository.deleteById(messageId);
    }
}
