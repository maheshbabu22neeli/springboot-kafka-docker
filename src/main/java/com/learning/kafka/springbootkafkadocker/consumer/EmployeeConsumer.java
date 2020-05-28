package com.learning.kafka.springbootkafkadocker.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConsumer{

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeConsumer.class);

    @KafkaListener(
            topics = "${employee.topic.name}",
            groupId = "${employee.topic.groupId}",
            containerFactory = "employeeTopicConsumerContainerFactory"
    )
    public void onMessage(ConsumerRecord consumerRecord) {
        LOGGER.info("Message received from topic, body => {} ", consumerRecord.value());
        for(Header header : consumerRecord.headers()) {
            if (header.key().equals("correlationId")) {
                LOGGER.info("Kafka Header CorrelationId : {}", new String(header.value()));
            }
        }
    }
}
