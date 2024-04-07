package dev.lxqtpr.linda.lindaforumengine.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository <UserEntity, UUID> {
    Optional<UserEntity> findByUsername(String username);
}
