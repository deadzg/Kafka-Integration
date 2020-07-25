package kafka.micronaut.app.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
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
        kafkaProducer.sendMessage("key1", "value1");
        return "Kafka Producer Returned";
    }
}
