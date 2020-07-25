package kafka.micronaut.app.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import kafka.micronaut.app.MyProducer;
import org.apache.kafka.clients.producer.Producer;

import javax.inject.Inject;
import java.awt.print.Book;
import java.util.Random;

@Controller("/kafka")
public class KafkaController {

    @Inject
    private  MyProducer kafkaProducer;


    @Get(produces = MediaType.TEXT_PLAIN, value = "/producer")
    public String customer() {
        kafkaProducer.sendMessage("key1", "value1", "header1");

        Single<String> singleObservable =  kafkaProducer.sendBody("k2", Single.just("v2"));

        singleObservable.subscribe();


        return "Kafka Producer Returned";
    }
}
