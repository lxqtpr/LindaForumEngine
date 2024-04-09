package dev.lxqtpr.linda.lindaforumengine.domain.user.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ResponseUserDto{
    private UUID id;
    private String username;
    private String accessToken;
    private String refreshToken;
    private String role;
}