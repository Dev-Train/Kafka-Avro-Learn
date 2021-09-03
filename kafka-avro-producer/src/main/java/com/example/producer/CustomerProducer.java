package com.example.producer;

import com.example.avro.data.Avro01;
import com.example.avro.data.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: ConsumerProducer
 * Inside the package - com.example.producer
 * Created Date: 7/20/2021
 * Created Time: 9:17 PM
 **/
@Service
@RequiredArgsConstructor
public class CustomerProducer {

    private final KafkaTemplate<String, Customer> kafkaTemplate;

    public void sendCustomer(Customer customer){
        kafkaTemplate.send("t.customer", "test-customer", customer);
    }
}
