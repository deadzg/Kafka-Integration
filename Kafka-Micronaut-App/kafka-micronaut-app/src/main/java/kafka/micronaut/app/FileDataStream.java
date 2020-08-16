package kafka.micronaut.app;

import io.micronaut.configuration.kafka.streams.ConfiguredStreamBuilder;
import io.micronaut.context.annotation.Factory;
import kafka.micronaut.app.model.FileUploadKey;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Produced;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Properties;

@Factory
public class FileDataStream {
    public static final String FILE_DATA_STREAM = "file-data-stream";
    public static final String INPUT = "upload-file-topic";
    public static final String OUTPUT = "upload-file-topic-output";

    @Singleton
    @Named(FILE_DATA_STREAM)
    KStream<FileUploadKey, byte[]> fileDataStreamProcessor(ConfiguredStreamBuilder builder) {

        //Serde<FileUploadKey> fileUploadKeySerde = Serdes.serdeFrom(new FileUploadSer(), new FileUploadDe());
        //Serde<byte[]> byteArraySerde = new Serdes.ByteArraySerde();

        Properties props = builder.getConfiguration();
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, FileUploadKeySerDe.class);
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.ByteArraySerde.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KStream<FileUploadKey, byte[]> source = builder
                .stream(INPUT);
        source.to(OUTPUT, Produced.with(new FileUploadKeySerDe(), new Serdes.ByteArraySerde()));

        return source;

//        KTable<FileUploadKey, byte[]> userDataTable = builder.table(INPUT);
//
//        userDataTable.toStream().print(Printed.<FileUploadKey, byte[]>toSysOut().withLabel("User Data Table"));
//
//        return userDataTable;
    }


}
