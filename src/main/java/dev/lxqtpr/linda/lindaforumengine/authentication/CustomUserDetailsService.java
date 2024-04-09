package dev.lxqtpr.linda.lindaforumengine.authentication;

import dev.lxqtpr.linda.lindaforumengine.core.exceptions.ResourceNotFoundException;
import dev.lxqtpr.linda.lindaforumengine.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User with this username not found"));
        return new CustomUserDetails(user);
    }
}