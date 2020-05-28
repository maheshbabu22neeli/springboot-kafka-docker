package com.learning.kafka.springbootkafkadocker.producer.base;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class KafkaProducer extends KafkaTemplate {

    @Autowired
    public KafkaProducer(ProducerFactory producerFactory) {
        super(producerFactory);
    }

    public final ListenableFuture sendMessage(final ProducerRecord producerRecord) {
        return this.doSend(producerRecord);
    }
}
