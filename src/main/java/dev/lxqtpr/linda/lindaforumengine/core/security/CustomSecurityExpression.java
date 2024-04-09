package dev.lxqtpr.linda.lindaforumengine.core.security;

import dev.lxqtpr.linda.lindaforumengine.authentication.CustomUserDetails;
import dev.lxqtpr.linda.lindaforumengine.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("securityExpression")
@RequiredArgsConstructor
public class CustomSecurityExpression {
    private final UserRepository userRepository;
    public boolean canAccessUserToMessage(UUID messageId){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var principal = (CustomUserDetails) authentication.getPrincipal();
        return userRepository.isMessageOwner(principal.getId(), messageId) || principal.getRole().equals("ROLE_ADMIN");
    }
}