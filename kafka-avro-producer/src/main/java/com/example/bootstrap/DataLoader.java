package com.example.bootstrap;

import com.example.avro.data.Avro01;
import com.example.producer.KafkaAvroProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: DataLoader
 * Inside the package - com.example.bootstrap
 * Created Date: 7/15/2021
 * Created Time: 7:34 AM
 **/
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final KafkaAvroProducer producer;

    @Override
    public void run(String... args) throws Exception {
        Avro01 avro01 = Avro01.newBuilder()
                .setFullName("Balaji")
                .setMaritalStatus("Married")
                .setActive(true)
                .build();
        producer.send(avro01);
    }
}
