package com.smalwe.kafka.explore;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class Application {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Kafka Exploration");

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:29092");
        props.put("acks", "all");
        props.put("linger.ms", 1);

        SimpleProducer simpleProducer = new SimpleProducer(props);
        simpleProducer.produceNumbersSmall();


        SimpleProducerV2 simpleProducerV2 = new SimpleProducerV2();
        simpleProducerV2.producerNumbers();
    }
}
