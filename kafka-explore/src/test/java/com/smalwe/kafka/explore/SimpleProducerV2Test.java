package com.smalwe.kafka.explore;

import com.smalwe.kafka.explore.partitioner.OddEvenPartitioner;
import com.sun.source.tree.AssertTree;
import org.apache.kafka.clients.producer.MockProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.Node;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Test scenarios
 * - Number of messages produced
 * - Retry scenario
 * - Exception Handling
 * - Specific Partition
 * - Transaction
 */
public class SimpleProducerV2Test {

    @Test
    public void testProduceNumbers_numberofMessagesSent() throws ExecutionException, InterruptedException {

        MockProducer<String, String> mockProducer = new MockProducer<>(true, new StringSerializer(), new StringSerializer());
        SimpleProducerV2 simpleProducerV2 = new SimpleProducerV2();

        simpleProducerV2.setProducer(mockProducer);
        simpleProducerV2.producerNumbers();

        Assert.assertTrue(mockProducer.history().size() == 5);

    }


}
