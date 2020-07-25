    package kafka.micronaut.app;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaListener(offsetReset = OffsetReset.EARLIEST)
public class MyConsumer {

    @Topic("my-topic")
    public void receive(@KafkaKey String key, String val) {
        System.out.println("Key:" + key + " Value:" + val);
    }
}