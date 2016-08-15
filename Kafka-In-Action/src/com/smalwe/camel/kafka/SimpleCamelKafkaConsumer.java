package com.smalwe.camel.kafka;

import org.apache.camel.CamelContext;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SimpleCamelKafkaConsumer {
	
	public static void main(String[] args) throws Exception {
		ApplicationContext appContext = new FileSystemXmlApplicationContext("config/kafkaCamelContext.xml");
		CamelContext camelContext = SpringCamelContext.springCamelContext(appContext, false);
		for(String s : camelContext.getComponentNames()) {
			System.out.println(s);
		}
		
		System.out.println("Starting camel context..");
		camelContext.start();
		Thread.currentThread().join();
	}
}
