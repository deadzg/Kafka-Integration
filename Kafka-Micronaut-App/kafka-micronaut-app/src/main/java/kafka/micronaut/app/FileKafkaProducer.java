package kafka.micronaut.app;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.context.annotation.Property;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.messaging.annotation.Body;
import io.micronaut.messaging.annotation.Header;
import kafka.micronaut.app.model.FileUploadKey;
import org.apache.kafka.clients.producer.ProducerConfig;

@KafkaClient(id="file-producer-id",
        timestamp = true,
        properties = @Property(name= ProducerConfig.RETRIES_CONFIG, value="15"))
@Header(name="X-Token", value="${micronaut.application.token}")
public interface FileKafkaProducer {

    @Topic("upload-file-topic")
    void sendMessage(@KafkaKey FileUploadKey key, @Body byte[] val);
}
