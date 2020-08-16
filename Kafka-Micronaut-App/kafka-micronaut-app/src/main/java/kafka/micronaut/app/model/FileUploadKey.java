package kafka.micronaut.app.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FileUploadKey {


    private String userId;

    private String fileName;


    @JsonCreator
    public FileUploadKey(@JsonProperty("userId") String userId, @JsonProperty("fileName") String fileName) {
        this.userId = userId;
        this.fileName = fileName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "FileUploadKey{" + "userId='" + userId + '\'' + ", fileName='" + fileName + '\'' + '}';
    }
}
