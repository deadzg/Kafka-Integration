package com.smalwe.kafka;

import java.util.Properties;
import java.util.Scanner;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class SimpleProducer {

	private  Properties props ;
	
	public SimpleProducer() {
	   props = new Properties();
	    
	    // Set the broker list for requesting metadata to find the lead broker
	    props.put("bootstrap.servers", "localhost:9092");
	    props.put("partitioner.class", "com.smalwe.kafka.SimplePartitioner");
	    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	    
	    //This specifies the serializer class for keys 
	    //props.put("serializer.class", "kafka.serializer.StringEncoder");
	    
	    // 1 means the producer receives an acknowledgment once the lead replica 
	    // has received the data. This option provides better durability as the 
	    // client waits until the server acknowledges the request as successful.
	    //props.put("request.required.acks", "1");
 
	    Producer<String, String> producer = new KafkaProducer<>(props);
	    producer.send(new ProducerRecord<String, String>("test", "Test", "azureTest"));
	    System.out.println("Message sent");
	    producer.close();
	  }


	public static void main (String args[]) {
		System.out.println("Hello");
		SimpleProducer simpleProducer = new SimpleProducer();
	/*	Scanner sc=new Scanner(System.in);  
		try {
			while(true) {
				 
					System.out.println("Type in MessageType:");
					String msgType = sc.next();
					System.out.println("Type in SchemaNumber:");
					String schema = sc.next();					
					new SimpleProducer(msgType, schema);
			 }
		}
		finally {
			sc.close();
		}*/	
	}	
}
