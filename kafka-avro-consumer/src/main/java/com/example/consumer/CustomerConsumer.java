package com.example.consumer;

import com.example.avro.data.Avro01;
import com.example.avro.data.Customer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: CustomerConsumer
 * Inside the package - com.example.consumer
 * Created Date: 7/20/2021
 * Created Time: 9:22 PM
 **/
@Service
@Slf4j
public class CustomerConsumer {

    @KafkaListener(topics = "t.customer")
    public void consume(ConsumerRecord<String, Customer> record) throws InstantiationException, IllegalAccessException, IOException {
        log.info("Key:{}, Value: {}", record.key(), record.value());
        Customer customer = record.value();
        log.info("First Name: {}", customer.getFirstName());
        log.info("Last Name: {}", customer.getLastName());
        customer.getCustomerAddress().stream().forEach(customerAddress -> {
            log.info("Address Type: {}", customerAddress.getAddressType());
            log.info("Address Line 1: {}", customerAddress.getAddressLine1());
            log.info("Address Line 2: {}", customerAddress.getAddressLine2());
            log.info("City: {}", customerAddress.getCity());
            log.info("State: {}", customerAddress.getState());
            log.info("Zip Code: {}", customerAddress.getZipCode());
        });
    }
}
