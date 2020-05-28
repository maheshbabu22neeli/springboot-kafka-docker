package com.learning.kafka.springbootkafkadocker.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.learning.kafka.springbootkafkadocker.model.Employee;
import com.learning.kafka.springbootkafkadocker.producer.EmployeeProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeService {

    private static Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    private EmployeeProducer employeeProducer;

    @Autowired
    public EmployeeService(EmployeeProducer employeeProducer) {
        this.employeeProducer = employeeProducer;
    }

    public void publishEmployeeMessageToKafka(final Employee employee) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        LOGGER.info("About to publish employee {}", mapper.writeValueAsString(employee));
        employeeProducer.publishEmployeeMessage(employee);

    }

}
