package dev.lxqtpr.linda.lindaforumengine.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository <UserEntity, UUID> {
    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);

    @Query(value = """
             SELECT exists(
                           SELECT 1
                           FROM message_entity
                           WHERE author_id = :userId
                             AND id = :messageId)
            """, nativeQuery = true)
    boolean isMessageOwner(@Param("userId") UUID userId, @Param("messageId") UUID messageId);
}
