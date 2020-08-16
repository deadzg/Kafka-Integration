package kafka.micronaut.app;

import kafka.micronaut.app.model.FileUploadKey;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class FileUploadKeySerDe implements Serde<FileUploadKey> {

    final private FileUploadSer serializer = new FileUploadSer();
    final private FileUploadDe deserializer = new FileUploadDe();


    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        serializer.configure(configs, isKey);
        deserializer.configure(configs, isKey);
    }

    @Override
    public void close() {
        serializer.close();
        deserializer.close();
    }

    @Override public Serializer<FileUploadKey> serializer() {
        return serializer;
    }

    @Override public Deserializer<FileUploadKey> deserializer() {
        return deserializer;
    }
}
