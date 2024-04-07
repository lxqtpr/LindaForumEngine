package dev.lxqtpr.linda.lindaforumengine.core.configs;

import dev.lxqtpr.linda.lindaforumengine.domain.message.MessageEntity;
import dev.lxqtpr.linda.lindaforumengine.domain.message.dto.ResponseMessageDto;
import dev.lxqtpr.linda.lindaforumengine.domain.topic.TopicEntity;
import dev.lxqtpr.linda.lindaforumengine.domain.topic.dto.CreateTopicDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;


@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper mapper = new ModelMapper();
        mapper
                .createTypeMap(CreateTopicDto.class, TopicEntity.class)
                .addMapping(CreateTopicDto::getTopicName, TopicEntity::setName);
        mapper
                .createTypeMap(MessageEntity.class, ResponseMessageDto.class)
                .addMapping(source -> source.getAuthor().getUsername(), ResponseMessageDto::setAuthor);

        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(PRIVATE);
        return mapper;
    }
}