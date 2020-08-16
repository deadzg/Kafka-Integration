package kafka.micronaut.app.service;

import io.micronaut.http.multipart.CompletedFileUpload;
import kafka.micronaut.app.FileKafkaProducer;
import kafka.micronaut.app.controller.KafkaController;
import kafka.micronaut.app.model.FileUploadKey;
import kafka.micronaut.app.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;

@Singleton
public class FileUploadService implements IFileUploadService {

    protected static final Logger LOG = LoggerFactory.getLogger(FileUploadService.class);

    @Inject
    FileUtils fileUtils;

    @Inject
    private FileKafkaProducer fileKafkaProducer;

    /**
     * Method to publish file to Kafka Topic
     * @param userId
     * @param file
     */
    @Override
    public void publishFileToTopic(String userId, CompletedFileUpload file) throws IOException {
        /*
        - Validate file is of time doc/pdf/txt
        - Create FileUploadKey object
        - Get bytes from file object
        - Create value object for Kafka to publish
         */
        String fileName = file.getFilename();

        if(fileUtils.validateFileType(fileName)) {

            LOG.info("Uploaded file type is valid");
            FileUploadKey fileUploadKey = new FileUploadKey(userId, file.getFilename());

            LOG.info("Sending file to kafka topic...");

            byte[] b = file.getBytes();

            LOG.debug("Byte length:" + b.length);

            fileKafkaProducer.sendMessage(fileUploadKey, b);

            LOG.info("File sent to kafka topic");

        } else {
            LOG.info("Uploaded file type is not valid");
            throw (new IllegalArgumentException("File type not supported"));
        }


    }
}
