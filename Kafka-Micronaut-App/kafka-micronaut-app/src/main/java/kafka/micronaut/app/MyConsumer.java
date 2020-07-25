    package kafka.micronaut.app;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaListener(groupId = "my-consumer", offsetReset = OffsetReset.EARLIEST, threads = 2)
public class MyConsumer {

    /*
    https://kafka.apache.org/21/javadoc/org/apache/kafka/clients/consumer/ConsumerRecord.html
    receive internally implements ConsumerRecord
     */
    @Topic({"my-topic"})
    public void receive(@KafkaKey String key, String val, int partition, String topic, long timestamp) {
        System.out.println("Key:" + key + " Value:" + val + "Timestamp:" + timestamp + " Topic:" + topic);
    }
}