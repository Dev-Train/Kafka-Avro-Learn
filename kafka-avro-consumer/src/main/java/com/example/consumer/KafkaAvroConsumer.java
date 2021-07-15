package com.example.consumer;

import com.example.avro.data.Avro01;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: KafkaAvroConsumer
 * Inside the package - com.example.consumer
 * Created Date: 7/15/2021
 * Created Time: 8:17 AM
 **/
@Service
@Slf4j
public class KafkaAvroConsumer {

    @KafkaListener(topics = "sc-avro01")
    public void consume(ConsumerRecord<String, Avro01> record){
        log.info("{} : {}", record.key(), record.value());
        Avro01 avro01 = record.value();
        log.info("Name: {}, Marital Status: {}, Active: {}", avro01.getFullName(), avro01.getMaritalStatus(), avro01.getActive());
    }
}
