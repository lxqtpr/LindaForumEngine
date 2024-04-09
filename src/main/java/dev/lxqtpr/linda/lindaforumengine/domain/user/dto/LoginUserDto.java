package dev.lxqtpr.linda.lindaforumengine.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginUserDto {

    @NotBlank(message = "Password is mandatory")
    private String password;

    @NotBlank(message = "Username is mandatory")
    private String username;
}
