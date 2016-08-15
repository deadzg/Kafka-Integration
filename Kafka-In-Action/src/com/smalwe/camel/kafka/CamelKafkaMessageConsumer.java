package com.smalwe.camel.kafka;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.kafka.KafkaConstants;
import org.springframework.stereotype.Component;

@Component
public class CamelKafkaMessageConsumer implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		
		Thread.sleep(50000); //This is for generating group dead and removed scenario
		System.out.println("Inside the camelKafkaMessageConsumer");
		System.out.println("Message:" + exchange.getIn().getBody().toString());
		System.out.println("Partition Key:" + exchange.getIn().getHeader(KafkaConstants.PARTITION_KEY));
		System.out.println("Partition:" + exchange.getIn().getHeader(KafkaConstants.PARTITION));
		System.out.println("Topic:" + exchange.getIn().getHeader(KafkaConstants.TOPIC));
		System.out.println("Offset:" + exchange.getIn().getHeader(KafkaConstants.OFFSET));
	}
	
}
