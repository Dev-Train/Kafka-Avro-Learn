package com.example.consumer;

import com.example.avro.data.Avro01;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

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
    public void consume(ConsumerRecord<String, Avro01> record) throws InstantiationException, IllegalAccessException, IOException {
        //ByteArrayDeserializer
        //      -- value-deserializer: io.confluent.kafka.serializers.KafkaAvroDeserializer
        //Avro01 avro01 = (Avro01) record.value();
        log.info("{} : {}", record.key(), record.value());
        //DatumReader<GenericRecord> datumReader = new SpecificDatumReader<>(Avro01.class.newInstance().getSchema());
        //Decoder decoder = DecoderFactory.get().binaryDecoder(record.value().toByteBuffer().array(), null);
        //ObjectMapper objectMapper = new ObjectMapper();
        //String converted = convertPayloadToJson(record.value());
        //Avro01 avro01 = (Avro01) datumReader.read(null, decoder);;
        //log.info("Converted {}", converted);
        Avro01 avro01 = (Avro01) record.value();
        //Avro01 avro01 = (Avro01) convertJsonToObject(Optional.of(converted),Avro01.class);
        log.info("Name: {}, Marital Status: {}, Active: {}", avro01.getFullName(), avro01.getMaritalStatus(), avro01.getActive());
    }

    public String convertPayloadToJson(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            //log.error(e);
        }
        return null;
    }

    public Object convertJsonToObject(final Optional<String> jsonString, final Class obj) {
        try {
            if (jsonString.isPresent()) {
                final ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(jsonString.get(), obj);
            }

        } catch (JsonProcessingException e) {
            //log.error(e);
        }
        return Optional.empty();
    }
}
