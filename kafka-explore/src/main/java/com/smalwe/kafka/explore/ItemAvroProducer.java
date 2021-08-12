package com.smalwe.kafka.explore;

import com.smalwe.kafka.explore.model.Item;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class ItemAvroProducer {

    private final static String TOPIC = "item";

    private static Producer<String, Item> createProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "AvroProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                KafkaAvroSerializer.class.getName());
        props.put(
                KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG,
                "http://localhost:8081");

        return new KafkaProducer<>(props);
    }

    public static void main(String... args) {

        Producer<String, Item> producer = createProducer();

        IntStream.range(1, 10).forEach(index->{
            Future<RecordMetadata> recordMetaData = producer.send(new ProducerRecord<>(TOPIC, "key" + index, generateItem(index)));

            try {
                System.out.println("Offset:" + recordMetaData.get().offset() +
                        " Partition:" + recordMetaData.get().partition() +
                        " Serialized Value Size:" + recordMetaData.get().serializedValueSize() +
                        " Serialized Key Size:" + recordMetaData.get().serializedKeySize() +
                        " Timestamp" + recordMetaData.get().timestamp());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        });

        producer.flush();
        producer.close();
    }

    /**
     * Method to generate a random item
     * @param i
     * @return
     */
    private static Item generateItem(int i) {
        Random r = new Random();
        return new Item("name" + i, "food", r.nextFloat());
    }

}
