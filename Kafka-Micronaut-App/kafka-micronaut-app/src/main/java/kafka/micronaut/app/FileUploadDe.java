package kafka.micronaut.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.http.multipart.CompletedFileUpload;
import kafka.micronaut.app.model.FileUploadKey;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class FileUploadDe implements Deserializer<FileUploadKey> {

    @Override public void close() {

    }

    @Override public void configure(Map<String, ?> arg0, boolean arg1) {

    }

    @Override
    public FileUploadKey deserialize(String arg0, byte[] arg1) {
        ObjectMapper mapper = new ObjectMapper();
        FileUploadKey user = null;
        try {
            user = mapper.readValue(arg1, FileUploadKey.class);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return user;
    }

}