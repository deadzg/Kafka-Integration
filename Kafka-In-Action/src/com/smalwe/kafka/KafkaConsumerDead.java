package com.smalwe.kafka;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;


/**
 * This code generates the scenario where consumers are marked dead
 * Reason: Sometimes deafult time (session.timeout.ms = 30000) is not sufficient enough to process the messages 
 * from topic, thus consumer ends up processing the messages more than session.timeout.ms, which results in not sending
 * a heartbeat to the broker/coordinator , thus broker marks the consumer as dead.
 * In order to overcome such scenario, always mark the session.timeout.ms > what you think the message processing will take
 * Also need to mark request.timeout.ms > session.timeout.ms
 * Prior to above settings on consumer side, you need to set the group.max.session.timeout.ms > session.timeout.ms on the broker side
 * and restart the broker
 * 
 * 
 * Here I made the sleep time > session.timeout.ms t, thus you can observe in your kafka logs that the
 * consumer is marked dead with the below message
 * INFO [GroupCoordinator 0]: Group group1 generation 1 is dead and removed (kafka.coordinator.GroupCoordinator)
 * 
 * You can read more about this in the below link:
 * https://cwiki.apache.org/confluence/display/KAFKA/Kafka+0.9+Consumer+Rewrite+Design#Kafka0.9ConsumerRewriteDesign-Groupmanagementprotocol
 * 
 * @author soumalwe
 * Aug 15, 2016
 */
public class KafkaConsumerDead {
	
	public static void main(String args[]) throws InterruptedException  {
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("group.id", "group1");
		//props.put("heartbeat.interval.ms", "50000");
		props.put("session.timeout.ms", "50000");
		props.put("request.timeout.ms", "60000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String,String>(props);
		
		consumer.subscribe(Collections.singletonList("test"));
		
		try {
			while(true){
				
				ConsumerRecords<String, String> records = consumer.poll(100);
				Thread.sleep(70000);
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
