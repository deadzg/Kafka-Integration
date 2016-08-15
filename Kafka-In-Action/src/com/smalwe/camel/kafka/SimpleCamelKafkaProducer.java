package com.smalwe.camel.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SimpleCamelKafkaProducer {

	private static ProducerTemplate producer;
	
	public static void main(String[] args) throws Exception {
		ApplicationContext appContext = new FileSystemXmlApplicationContext("config/kafkaProducerCamelContext.xml");
		CamelContext camelContext = SpringCamelContext.springCamelContext(appContext, false);
		for(String s : appContext.getBeanDefinitionNames()) {
			System.out.println(s);
		}
		
		System.out.println("Starting camel context..");
		camelContext.start();
		
		Map<String, Object> headerMap = new HashMap<>();
		headerMap.put(KafkaConstants.PARTITION_KEY, "1");
		headerMap.put(KafkaConstants.KEY, "12");
	
		
		
		//producer.sendBodyAndHeaders("seda:db", "Camel Kafka test message " , headerMap);
		producer.sendBodyAndHeader("IT test message", KafkaConstants.KEY, "1");
		Thread.currentThread().join();
		
		
		
	}

	public static ProducerTemplate getProducer() {
		return producer;
	}

	public static void setProducer(ProducerTemplate producer) {
		SimpleCamelKafkaProducer.producer = producer;
	}
	
}


