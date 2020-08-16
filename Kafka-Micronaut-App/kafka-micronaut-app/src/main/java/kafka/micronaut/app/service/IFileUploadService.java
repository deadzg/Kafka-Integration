package kafka.micronaut.app.service;

import io.micronaut.http.multipart.CompletedFileUpload;

import java.io.IOException;

public interface IFileUploadService {

    void publishFileToTopic(String userId, CompletedFileUpload file) throws IOException;
}
