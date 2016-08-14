package com.smalwe.kafka;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class SimpleConsumer {
	
	public static void main(String args[]) throws InterruptedException  {
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("group.id", "group1");
		//props.put("heartbeat.interval.ms", "50000");
		//props.put("session.timeout.ms", "50000");
		//props.put("request.timeout.ms", "60000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String,String>(props);
		
		consumer.subscribe(Collections.singletonList("test"));
		
		try {
			while(true){
				
				ConsumerRecords<String, String> records = consumer.poll(100);
				//Thread.sleep(70000);
				for (ConsumerRecord<String, String> record : records) {
				/*	log.debug("topic = %s, partition = %s, offset = %d, customer = %s,country = %s\n",
							record.topic(), record.partition(), record.offset(), record.key(),
						    record.value());*/
					System.out.println("Consumer:" + SimpleConsumer.class.getName());
					System.out.println("Topic:" + record.topic());
					System.out.println("Partition:" + record.partition());
					System.out.println("Offset:" + record.offset());
					System.out.println("Key:" + record.key());
					System.out.println("Value:" + record.value());

				}
			}
		}
		finally{
			consumer.close();
		}
		
		
	}
}
