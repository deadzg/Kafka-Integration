    package kafka.micronaut.app;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.context.annotation.Property;
import io.micronaut.messaging.annotation.Body;
import io.micronaut.messaging.annotation.Header;
import io.reactivex.Single;
import org.apache.kafka.clients.producer.ProducerConfig;

    /**
 * Refer Kafka Producer Documentation for the optimal use of KafkaClient:
 * https://kafka.apache.org/10/javadoc/org/apache/kafka/clients/producer/KafkaProducer.html
 * Producer Id helps configure seperate config for each producer
 */

@KafkaClient(id="my-producer-id",
                timestamp = true,
                properties = @Property(name= ProducerConfig.RETRIES_CONFIG, value="15"))
@Header(name="X-Token", value="${micronaut.application.token}")
public interface MyProducer {

    @Topic("my-topic")
    void sendMessage(@KafkaKey String key, @Body String val, @Header("my-topic-header") String myHeader);

    @Topic("my-topic-single")
    Single<String> sendBody(@KafkaKey String key, @Body Single<String> val);

}