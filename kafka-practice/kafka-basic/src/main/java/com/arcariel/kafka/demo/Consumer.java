package com.arcariel.kafka.demo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class Consumer {
    private static final Logger LOG = LoggerFactory.getLogger(Consumer.class.getSimpleName());

    private static String username = System.getenv("UPSTASH_USERNAME");
    private static String password = System.getenv("UPSTASH_PASSWORD");
    public static void main(String[] args) {
        LOG.info("Kafka consumer is running!");

        String groupId = "java-application";
        String topic = "demo_java";

        //Kafka connection Properties
        Properties props = new Properties();
        props.put("bootstrap.servers", "correct-goblin-8702-us1-kafka.upstash.io:9092");
        props.put("sasl.mechanism", "SCRAM-SHA-256");
        props.put("security.protocol", "SASL_SSL");
        props.put("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required username=\""+username+"\" password=\""+password+"\";");

        //Consumer
        //Serializer
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());

        props.put("group.id", groupId);
        props.put("auto.offset.reset", "earliest");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);


        final Thread mainThread = Thread.currentThread();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                LOG.info("Shutdown detected...");
                consumer.wakeup();
                try {
                    mainThread.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        try {
            //subscribe to a topic
            consumer.subscribe(Arrays.asList(topic));

            //get data from topic
            while (true) {
                LOG.info("Polling data");
                ConsumerRecords<String, String> records =
                        consumer.poll(Duration.ofMillis(1000));

                for (ConsumerRecord<String, String> record : records) {
                    //process the data
                    LOG.info("key: " + record.key() + ", Value: " + record.value());
                    LOG.info("Partition: " + record.partition() + ", Offset: " + record.offset());
                }
            }
        } catch (WakeupException we) {
            LOG.info("Consumer Shutting down");
        } catch (Exception e) {
            LOG.error("Unexpected exception on Consumer", e);
        } finally {
            consumer.close(); //commit offset correctly
            LOG.info("Consumer down");
        }

    }
}
