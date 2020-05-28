package com.learning.kafka.springbootkafkadocker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learning.kafka.springbootkafkadocker.model.Employee;
import com.learning.kafka.springbootkafkadocker.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(path = "/employee")
    public ResponseEntity post(@RequestBody Employee employee) throws JsonProcessingException {

        employeeService.publishEmployeeMessageToKafka(employee);

        return new ResponseEntity(HttpStatus.OK);
    }

}
