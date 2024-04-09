package dev.lxqtpr.linda.lindaforumengine.domain.message;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/message")
@RequiredArgsConstructor
@RestController
public class MessageController {
    private final MessageService messageService;

    @PreAuthorize("@securityExpression.canAccessUserToMessage(#messageId)")
    @DeleteMapping("/{messageId}")
    public void deleteMessage(@PathVariable UUID messageId){
        messageService.deleteMessage(messageId);
    }
}
