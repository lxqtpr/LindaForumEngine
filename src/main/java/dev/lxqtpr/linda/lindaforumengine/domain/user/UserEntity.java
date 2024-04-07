package dev.lxqtpr.linda.lindaforumengine.domain.user;

import dev.lxqtpr.linda.lindaforumengine.domain.message.MessageEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String username;

    private String password;

    private String email;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MessageEntity> messages;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;
}