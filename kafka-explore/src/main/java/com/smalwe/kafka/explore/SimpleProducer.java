package com.smalwe.kafka.explore;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Notes:
 *  - Generate BufferExhaustedException
 *  - Record the metadata for each message produced
 *  - Get the buffer size before it is sent to kafka
 *  - Generate and implement transaction scenario
 *
 *  Questions:
 *  - Is Future Blocking? - Future get() method is blocking
 */
public class SimpleProducer {

    private Properties producerProperties;


    public SimpleProducer(Properties producerProperties) {
        this.producerProperties = producerProperties;
        this.producerProperties .put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        this.producerProperties .put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        this.producerProperties.put(ProducerConfig.BATCH_SIZE_CONFIG, 100 );
        this.producerProperties.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 60000 );
        this.producerProperties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 100 );
        this.producerProperties.put("retries", 0);
    }

    public void produceNumbers() throws ExecutionException, InterruptedException {

        Future<RecordMetadata> recordMetaData = null;
        Producer<String, String> producer = new KafkaProducer<>(producerProperties);
        for (int i = 0; i < 10; i++) {
            recordMetaData = producer.send(new ProducerRecord<String, String>("my-topic", Integer.toString(i), Integer.toString(i)));
            System.out.println("Offset:" + recordMetaData.get().offset() +
                                " Partition:" + recordMetaData.get().partition() +
                                " Serialized Value Size:" + recordMetaData.get().serializedValueSize() +
                                " Serialized Key Size:" + recordMetaData.get().serializedKeySize() +
                                " Timestamp" + recordMetaData.get().timestamp());
        }


        producer.close();
    }

    public void produceNumbersSmall() throws ExecutionException, InterruptedException {

        Future<RecordMetadata> recordMetaData = null;
        Producer<String, String> producer = new KafkaProducer<>(producerProperties);
        for (int i = 0; i < 5; i++) {
            recordMetaData = producer.send(new ProducerRecord<String, String>("my-topic", Integer.toString(i), Integer.toString(i)));
            System.out.println("Offset:" + recordMetaData.get().offset() +
                    " Partition:" + recordMetaData.get().partition() +
                    " Serialized Value Size:" + recordMetaData.get().serializedValueSize() +
                    " Serialized Key Size:" + recordMetaData.get().serializedKeySize() +
                    " Timestamp" + recordMetaData.get().timestamp());
        }


        producer.close();
    }

}
