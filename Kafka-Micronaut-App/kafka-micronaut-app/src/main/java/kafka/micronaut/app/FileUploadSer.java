package kafka.micronaut.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.http.multipart.CompletedFileUpload;
import kafka.micronaut.app.model.FileUploadKey;
import org.apache.kafka.common.serialization.Serializer;

import javax.inject.Singleton;
import java.util.Map;

@Singleton
public class FileUploadSer implements Serializer<FileUploadKey> {

    @Override public void configure(Map<String, ?> map, boolean b) {

    }

    @Override public byte[] serialize(String arg0, FileUploadKey arg1) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsString(arg1).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;
    }

    @Override public void close() {

    }

}