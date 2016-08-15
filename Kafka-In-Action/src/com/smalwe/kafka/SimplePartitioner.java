package com.smalwe.kafka;

import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

public class SimplePartitioner implements Partitioner {

	public static boolean flag = false;
	
	@Override
	public void configure(Map<String, ?> configs) {
		// TODO Auto-generated method stub
		
	}

	
	
	@Override
	public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
		System.out.println("Came in partitioner with key:" + key);
		List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
		int ky = Integer.parseInt(key.toString());
		int partition = ky % 2;
		System.out.println("Partition Assigned:" + partition);
		return partition;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
	
}
