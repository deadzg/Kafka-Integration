package kafka.micronaut.app.service;

import io.micronaut.http.multipart.CompletedFileUpload;
import kafka.micronaut.app.controller.KafkaController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Singleton
public class FileRepo {

    protected static final Logger LOG = LoggerFactory.getLogger(FileRepo.class);

    public void upload(CompletedFileUpload file) {
        try {
            LOG.info("Creating input stream");
            InputStream inputStream = file.getInputStream();
            File fs =  new File("UploadedFiles", file.getFilename());
            Files.copy(inputStream,fs.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {

        }
    }

    public void upload(String key, byte[] file) {
        try {
            Path path = Paths.get("UploadedFiles/" + key);
            Files.write(path, file);
        } catch (IOException io) {
            LOG.error("Upload failed from consumer");
        }

    }
}
