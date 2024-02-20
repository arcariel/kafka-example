package com.arcariel.kafka.demo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class Producer {

    private static final Logger LOG = LoggerFactory.getLogger(Producer.class.getSimpleName());
    private static String username = System.getenv("UPSTASH_USERNAME");
    private static String password = System.getenv("UPSTASH_PASSWORD");
    public static void main(String[] args) {
        LOG.info("Kafka producer is running!");

        //Kafka connection Properties
        Properties props = new Properties();
        props.put("bootstrap.servers", "correct-goblin-8702-us1-kafka.upstash.io:9092");
        props.put("sasl.mechanism", "SCRAM-SHA-256");
        props.put("security.protocol", "SASL_SSL");
        props.put("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required username=\""+username+"\" password=\""+password+"\";");

        //Serializer
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());

        //create producer with properties
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        ProducerRecord<String, String> producerRecord = new ProducerRecord<>("demo_java", "hello world");

        producer.send(producerRecord);

        producer.flush();
        producer.close();
    }
}
