package com.smalwe.kafka.explore.partitioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.clients.producer.internals.DefaultPartitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class OddEvenPartitioner extends DefaultPartitioner {

    @Override
    public int partition(
            String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        if(((String)key).length() == 1) {
            return 0;
        }
            return 1;
    }

}
