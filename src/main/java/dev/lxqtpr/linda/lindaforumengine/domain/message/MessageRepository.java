package dev.lxqtpr.linda.lindaforumengine.domain.message;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface MessageRepository extends JpaRepository<MessageEntity, UUID>,
        PagingAndSortingRepository<MessageEntity, UUID> {
    Page<MessageEntity> findAllByTopicId(UUID topicId, Pageable pageable);
}
