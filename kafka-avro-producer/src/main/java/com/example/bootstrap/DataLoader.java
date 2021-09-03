package com.example.bootstrap;

import com.example.avro.data.AddressType;
import com.example.avro.data.Avro01;
import com.example.avro.data.Customer;
import com.example.avro.data.CustomerAddress;
import com.example.producer.CustomerProducer;
import com.example.producer.KafkaAvroProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

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

    private final CustomerProducer customerProducer;

    @Override
    public void run(String... args) throws Exception {
        Avro01 avro01 = Avro01.newBuilder()
                .setFullName("Balaji")
                .setMaritalStatus("Married")
                .setActive(true)
                .build();
        producer.send(avro01);

        CustomerAddress resAddress = CustomerAddress.newBuilder()
                .setAddressLine1("13377 Batten Lane")
                .setAddressLine2("line 2")
                .setCity("Odessa")
                .setState("FL")
                .setZipCode("33556")
                .setAddressType(AddressType.RES)
                .build();

        CustomerAddress mailAddress = CustomerAddress.newBuilder()
                .setAddressLine1("P.O. Box 3456")
                .setAddressLine2("line 2")
                .setCity("Odessa")
                .setState("FL")
                .setZipCode("33556")
                .setAddressType(AddressType.MAIL)
                .build();

        Customer customer = Customer.newBuilder()
                .setFirstName("Pooja")
                .setLastName("Mohanakrishnan")
                .setAge(36)
                .setAutomatedEmail(true)
                .setCustomerEmails(List.of("vba@gmail.com", "bck@outlook.com"))
                .setHeight(76.3f)
                .setWeight(65.2f)
                .setCustomerAddress(List.of(resAddress,mailAddress))
                .build();

        customerProducer.sendCustomer(customer);
    }
}
