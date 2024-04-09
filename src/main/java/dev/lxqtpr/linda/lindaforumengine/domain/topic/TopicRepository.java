package dev.lxqtpr.linda.lindaforumengine.domain.topic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface TopicRepository extends PagingAndSortingRepository<TopicEntity, UUID>, JpaRepository<TopicEntity,UUID> { }
