package com.arcariel.kafka.demo;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerWithKeys {

    private static final Logger LOG = LoggerFactory.getLogger(ProducerWithKeys.class.getSimpleName());

    private static String username = System.getenv("UPSTASH_USERNAME");
    private static String password = System.getenv("UPSTASH_PASSWORD");
    public static void main(String[] args) {
        LOG.info("Kafka producer with keys is running!");

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


        for (int j = 0 ; j<3; j++) {
            for (int i = 0; i<10 ; i++) {

                String topic = "demo_java";
                String key = "id_" + i;
                String value = "Value-" + i;

                ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, key, value);
                producer.send(producerRecord, new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata metadata, Exception e) {
                        if (e == null) {
                            LOG.info("Key: " + key + " | Partition: " + metadata.partition());
                        } else {
                            LOG.error("Error producing: ", e);
                        }
                    }
                });
            }
        }

        producer.flush();
        producer.close();
    }
}
