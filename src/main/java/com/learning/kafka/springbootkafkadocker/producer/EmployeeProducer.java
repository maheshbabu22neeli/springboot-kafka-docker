package com.learning.kafka.springbootkafkadocker.producer;

import com.learning.kafka.springbootkafkadocker.model.Employee;
import com.learning.kafka.springbootkafkadocker.producer.base.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class EmployeeProducer {

    private static Logger LOGGER = LoggerFactory.getLogger(EmployeeProducer.class);

    private KafkaProducer kafkaProducer;

    @Value("${employee.topic.name}")
    private String employeeTopicName;

    @Autowired
    public EmployeeProducer(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    public void publishEmployeeMessage(final Employee employee) {

        ProducerRecord employeeProducerRecord = prepareEmployeeProducerRecord(employee);
        kafkaProducer.sendMessage(employeeProducerRecord);
        LOGGER.info("Message Published");

    }

    private ProducerRecord prepareEmployeeProducerRecord(Employee employee) {

        // Here I am adding headers as well, just want to show how headers can also be send in the kafka message
        List<Header> headerList = new ArrayList<>();
        headerList.add(new RecordHeader("correlationId", UUID.randomUUID().toString().getBytes()));

        return new ProducerRecord(
                employeeTopicName,
                null,
                System.currentTimeMillis(),
                null,
                employee,
                headerList
        );

    }
}
