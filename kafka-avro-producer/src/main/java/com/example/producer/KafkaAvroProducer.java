package com.example.producer;

import com.example.avro.data.Avro01;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: KafkaAvroProducer
 * Inside the package - com.example.producer
 * Created Date: 7/15/2021
 * Created Time: 7:32 AM
 **/
@Service
@RequiredArgsConstructor
public class KafkaAvroProducer {

    private final KafkaTemplate<String, Avro01> kafkaTemplate;

    public void send(Avro01 data){
        kafkaTemplate.send("sc-avro01", "test-data", data);
    }
}
