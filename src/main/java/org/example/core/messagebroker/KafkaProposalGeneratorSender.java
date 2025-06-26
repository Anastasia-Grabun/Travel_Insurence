package org.example.core.messagebroker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.KafkaException;
import org.example.core.api.dto.AgreementDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Profile("postgresql-container")
public class KafkaProposalGeneratorSender implements ProposalGeneratorQueueSender {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProposalGeneratorSender.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topicName;
    private final ObjectMapper objectMapper;

    public KafkaProposalGeneratorSender(KafkaTemplate<String, String> kafkaTemplate,
                                        @Value("${kafka.topic.proposal-generation}") String topicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void send(AgreementDTO agreement) {
        try {
            String json = objectMapper.writeValueAsString(agreement);
            kafkaTemplate.send(topicName, json);
            logger.info("Sent message to Kafka topic {}: {}", topicName, json);
        } catch (JsonProcessingException e) {
            logger.error("Failed to serialize AgreementDTO", e);
        } catch (KafkaException e) {
            logger.error("Failed to send message to Kafka topic {}", topicName, e);
        }
    }
}
