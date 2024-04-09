package dev.lxqtpr.linda.lindaforumengine.authentication;

import dev.lxqtpr.linda.lindaforumengine.core.exceptions.JwtException;
import dev.lxqtpr.linda.lindaforumengine.core.exceptions.PasswordDoesNotMatchException;
import dev.lxqtpr.linda.lindaforumengine.core.exceptions.ResourceNotFoundException;
import dev.lxqtpr.linda.lindaforumengine.domain.user.RoleEnum;
import dev.lxqtpr.linda.lindaforumengine.domain.user.UserEntity;
import dev.lxqtpr.linda.lindaforumengine.domain.user.UserRepository;
import dev.lxqtpr.linda.lindaforumengine.domain.user.dto.CreateUserDto;
import dev.lxqtpr.linda.lindaforumengine.domain.user.dto.LoginUserDto;
import dev.lxqtpr.linda.lindaforumengine.domain.user.dto.ResponseUserDto;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public ResponseUserDto registration(CreateUserDto createUserDto) {
        if (userRepository.existsByUsername(createUserDto.getUsername()))
            throw new ResourceNotFoundException("User already exist");

        var userToSave = modelMapper.map(createUserDto, UserEntity.class);

        userToSave.setRole(RoleEnum.ROLE_USER);
        userToSave.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        var res = modelMapper.map(userRepository.save(userToSave), ResponseUserDto.class);
        res.setAccessToken(jwtService.generateAccessToken(userToSave));
        res.setRefreshToken(jwtService.generateRefreshToken(userToSave));

        return res;
    }

    public ResponseUserDto login(LoginUserDto loginUserDto){
        var user = userRepository.findByUsername(loginUserDto.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist"));
        if (!passwordEncoder.matches(loginUserDto.getPassword(), user.getPassword())){
            throw new PasswordDoesNotMatchException("Password does not match");
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserDto.getUsername(),
                        loginUserDto.getPassword()
                )
        );
        var res = modelMapper.map(user, ResponseUserDto.class);
        res.setAccessToken(jwtService.generateAccessToken(user));
        res.setRefreshToken(jwtService.generateRefreshToken(user));
        return res;
    }

    public ResponseUserDto refreshTokens(HttpServletRequest request){
        var authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new JwtException("invalid refreshToken");
        }
        var refreshToken = authHeader.substring(7);
        if (!jwtService.validateRefreshToken(refreshToken)){
            throw new JwtException("Refresh token does not valid");
        }
        var username = jwtService.getUsernameFromRefreshClaims(refreshToken);
        if (username == null) throw new JwtException("invalid refreshToken");

        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist"));

        var res = modelMapper.map(user, ResponseUserDto.class);

        res.setAccessToken(jwtService.generateAccessToken(user));
        res.setRefreshToken(jwtService.generateRefreshToken(user));
        return res;
    }
}