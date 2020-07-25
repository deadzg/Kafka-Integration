Excerpts of Kafka Paper:

Drawbacks with existing messaging framework:
 - JMS has no API to explicitly batch multiple messages into single request, thus each message requires full TCP/IP roundtrip
 - No easy way to partition and store messages on multiple m/c
- They assume near immediate consumption of messages, thus their performance degrades if messages are allowed to accumulate

Existing Log Aggregators in Market:
Scribe -> Facebook
HDFS Flume -> CloudEra
HedWig -> Yahoo!

Kafka Architechture:
A producer can publish messages to the topic. They are stored at a set of servers called brokers.. A consumer can subscribe to one or more topics from the brokers and consumer then subscribed messages by pulling data from the brokers.

It uses message stream iterator which never terminates

Each partition of a topic corresponds to logical log. A log is implemented as a set of segment files of approximately the same size (Eg: 1GB) Every time a publisher publishes message to a partition , the broker simply appends the message to the lst segment file. For better performance the segment file is flushed to disk only after configurable number of messages have been published or a certain amount of time has elapsed. A message is only exposed to the consumer after it is flushed.
Unlike typical message systems , a message stored in Kafka doesn’t have an explicit message id. Instead , each message is addressed by it’s logical offset in the log.
Explicit caching of messages in memory is avoided , instead Kafka relies on underlying files system page caching.
Unlike other messaging systems, Kafka information about how much each consumer has consumed is not maintained by the broker, but by the consumer itself.
A consumer can deliberatly rewind back to an old offset and re-consume data.
Partition within a topic is the smallest level of parallelism.
In order to truely balance  the load we require more partitions in a topic than the consumers in each group.
To facilitate coordination Zookeeper is used:
 -Detecting the addition and removal of brokers and consumers
-Triggering a rebalance process in each consumer when the above events happen
-Maintaining consumption relationship and keeping track of the consumed offset of each partition

Kafka only guarantees atleast once delivery
Kafka guarantees ordering
To avoid corruption Kafka stores a CRC for each message in log, if there is any I/O error on the broker , Kakfka run a recovery process to remove those messages with inconsistent CRCs.
Avro is used as the serialization protocol.

Kafka Consumers In Depth:
When producer produces messages, kafka server assigns an offset to that message.
Consumer is able to control the messages it wants to consume by setting or resetting the message offset.
We have two options  for managing offset while writing a consumer: Automatic and Manual

auto.offset.reset 
Possible values : largest, smallest

The auto.offset.reset config kicks in ONLY if your consumer group does not have a valid offset committed somewhere (2 supported offset storages now are Kafka and Zookeeper). And it also depends on what sort of consumer you use

You have a consumer in a consumer group group1 that has consumed 5 messages and died. Next time you start this consumer it won't even use that auto.offset.reset config and will continue from the place it died because it will just fetch the stored offset from the offset storage

You have messages in a topic (like you described) and you start a consumer in a new consumer group group2. There is no offset stored anywhere and this time the auto.offset.reset config will decide whether to start from the beginning of the topic (smallest) or from the end of the topic (largest)

The consumer keeps track of the offset of the last message it has processed, so it will always request messages with an offset higher than the last offset. This setup works when a consumer is functioning normally, but what happens if the consumer crashes, or you want to stop it for maintenance? In this case you would want the consumer to remember the offset of last message processed, so that it can start with the first unprocessed message. 

In order to ensure message persistence, Kafka uses two types of offset: The current offset is used to track messages consumed when the consumer is working normally. The committed offset also tracks the last message offset, but it sends that information to the Kafka server for persistent storage.

If the consumer goes down or is taken down for some reason, it can query the Kafka server for the last committed offset and resume message consumption as if no time has been lost. For its part, the Kafka broker stores this information in a topic called __consumer_offsets. This data is replicated to multiple brokers so that the broker won't ever lose the offsets. 

Offset Data Commit:
Auto Commit : set auto.commit = true
and set auto.commit.interval.ms = x ms
Once the above setting is done, the consumer will commit the messages in response to it’s poll call which is issues in the background as set auto.commit.interval.ms

Manual Commit: commitSync()  or commitAsync() can be used for manual commit.

Three ways to manage manual offsets:
seekToBeginning - When a consumer crashes it starts from start.
seekToEnd - When a consumer crashes it starts from the last message and ignore the previous ones
seek - Here the offset number can be set manually.

References: 
http://www.javaworld.com/article/3066873/big-data/big-data-messaging-with-kafka-part-2.html?page=2
http://stackoverflow.com/questions/32390265/what-determines-kafka-consumer-offsete
