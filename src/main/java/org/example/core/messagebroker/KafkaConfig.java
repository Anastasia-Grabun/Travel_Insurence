package org.example.core.messagebroker;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    public static final String TOPIC_PROPOSAL_GENERATION = "proposal-generation";

    @Bean
    public NewTopic proposalGenerationTopic() {
        return TopicBuilder.name(TOPIC_PROPOSAL_GENERATION)
                .partitions(1)
                .replicas(1)
                .build();
    }

}

