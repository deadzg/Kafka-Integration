package kafka.micronaut.app;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.messaging.annotation.Body;
import kafka.micronaut.app.controller.KafkaController;
import kafka.micronaut.app.model.FileUploadKey;
import kafka.micronaut.app.service.FileRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@KafkaListener(groupId = "file-upload-consumer", offsetReset = OffsetReset.LATEST, threads = 2)
public class FileKafkaConsumer {

    protected static final Logger LOG = LoggerFactory.getLogger(FileKafkaConsumer.class);

    @Inject
    FileRepo fr;
    /*
    https://kafka.apache.org/21/javadoc/org/apache/kafka/clients/consumer/ConsumerRecord.html
    receive internally implements ConsumerRecord
     */
    @Topic({"upload-file-topic"})
    public void receive(@KafkaKey FileUploadKey key,  byte[] val) {
        LOG.info("Key:" + key + " " + val.length);
        fr.upload(key.getFileName(), val);
    }
}