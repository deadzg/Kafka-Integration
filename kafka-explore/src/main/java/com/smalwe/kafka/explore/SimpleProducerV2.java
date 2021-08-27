package com.smalwe.kafka.explore;

import com.smalwe.kafka.explore.partitioner.OddEvenPartitioner;
import lombok.Setter;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

@Setter
public class SimpleProducerV2 {

    private Producer<String, String> producer;

    public SimpleProducerV2 () {
        Properties producerProperties = new Properties();
        producerProperties.put("bootstrap.servers", "localhost:29092");
        producerProperties.put("acks", "all");
        producerProperties.put("linger.ms", 1);
        producerProperties .put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerProperties .put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerProperties.put(ProducerConfig.BATCH_SIZE_CONFIG, 100 );
        producerProperties.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 60000 );
        producerProperties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 100 );
        producerProperties.put("retries", 0);
        producerProperties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, OddEvenPartitioner.class.getName());
        this.producer = new KafkaProducer<>(producerProperties);
    }


    public void producerNumbers() {
        IntStream.range(0,5).forEach(i -> producer.send(new ProducerRecord<String, String>("my-topic-v2",Integer.toString(i), Integer.toString(i))));
        producer.close();
    }


}
