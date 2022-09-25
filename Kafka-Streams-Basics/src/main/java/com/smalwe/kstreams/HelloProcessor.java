package com.smalwe.kstreams;

import org.apache.kafka.streams.processor.api.Processor;
import org.apache.kafka.streams.processor.api.ProcessorContext;
import org.apache.kafka.streams.processor.api.Record;

public class HelloProcessor implements Processor<Void, String, Void, Void> {

    @Override public void init(ProcessorContext<Void, Void> context) {
        Processor.super.init(context);
    }

    @Override public void process(Record<Void, String> record) {
        System.out.println("Processor API : Hello :" + record.value());
    }

    @Override public void close() {
        Processor.super.close();
    }
}
