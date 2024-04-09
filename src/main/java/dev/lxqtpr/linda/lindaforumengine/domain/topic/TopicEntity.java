package dev.lxqtpr.linda.lindaforumengine.domain.topic;

import dev.lxqtpr.linda.lindaforumengine.domain.message.MessageEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TopicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @CreationTimestamp
    private LocalDateTime created;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MessageEntity> messages = new ArrayList<>();

    public void addMessage(MessageEntity message){
        this.messages.add(message);
        message.setTopic(this);
    }
    public void removeMessage(MessageEntity message){
        this.messages.remove(message);
        message.setTopic(null);
    }
}