package kafka.micronaut.app.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import kafka.micronaut.app.FileKafkaProducer;
import kafka.micronaut.app.MyProducer;
import kafka.micronaut.app.service.FileRepo;
import kafka.micronaut.app.service.IFileUploadService;
import org.apache.kafka.clients.producer.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.awt.print.Book;
import java.io.IOException;
import java.util.Random;

@Controller("/kafka")
public class KafkaController {

    protected static final Logger LOG = LoggerFactory.getLogger(KafkaController.class);

    @Inject
    private  MyProducer kafkaProducer;

    @Inject
    private FileRepo fr;

    @Inject
    private IFileUploadService fileUploadService;


    @Get(produces = MediaType.TEXT_PLAIN, value = "/producer")
    public String customer() {
        kafkaProducer.sendMessage("key1", "value1", "header1");

        Single<String> singleObservable =  kafkaProducer.sendBody("k2", Single.just("v2"));

        singleObservable.subscribe();


        return "Kafka Producer Returned";
    }

    @Post(value = "/upload/{userId}", consumes = MediaType.MULTIPART_FORM_DATA)
    public HttpResponse uploadFile(String userId, CompletedFileUpload file) throws IOException {
//        LOG.info("Uploading...");
//        fr.upload(file);
//        LOG.info("Upload Complete");
        fileUploadService.publishFileToTopic(userId, file);
        return HttpResponse.created("Record created");
    }
}
