    package kafka.micronaut.app;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

    @KafkaClient
public interface MyProducer {

    @Topic("my-topic")
    void sendMessage(@KafkaKey String key, String val);

}