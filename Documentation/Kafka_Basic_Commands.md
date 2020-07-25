# Kafka Basic Commands

## Topic Management
- Create a topic: `bin/kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 2 --topic my-topic`

- Get topic list: `bin/kafka-topics --list --zookeeper localhost:2181`

- Describe topic: `bin/kafka-topics --zookeeper localhost:2181 --describe --topic my-topic`

- Purge a topic: `bin/kafka-topics --zookeeper localhost:2181 --alter --topic string_user_topic_dlq --config retention.ms=1000`

- Delete a topic: `bin/kafka-topics --zookeeper localhost:2181 --delete --topic my-topic`
Note: In config/server.properties delete.topic.enable=true

- Manage a topic: `kafka-configs.sh --zookeeper localhost:2181 --entity-type topics --alter --entity-name string_user_topic_dlq --add-config retention.ms=1000`
Note : Number of partitions can only be increased
	   Cannot change the replication factor

- Get number of messages in topic: `bin/kafka-run-class kafka.tools.GetOffsetShell --broker-list localhost:9093 --topic my-topic --time -1 --offsets 1 | awk -F ":" '{sum += $3} END {print sum}'`

- Check offset for a topic: `bin/kafka-run-class kafka.tools.GetOffsetShell --broker-list localhost:9093 --topic my-topic --time -1`

- Set auto topic creation false: 
`cd /confluent-5.1.0/etc/kafka/server.properties`
Add this at the end of the file: `auto.create.topics.enable=false`

- Print all the messages in a topic with headers
```kafkacat -b localhost:29092 -t my-topic -C \
  -f '\nKey (%K bytes): %k
  Value (%S bytes): %s
  Timestamp: %T
  Partition: %p
  Offset: %o
  Headers: %h\n'```

## Consumer/Producer Management
- CLI Consumer : `bin/kafka-console-consumer --bootstrap-server localhost:9092 --topic my-topic`

- CLI Consumer from beginning: `bin/kafka-console-consumer --bootstrap-server localhost:9092 --topic my-topic --from-beginning`

- CLI Producer: `bin/kafka-console-producer --topic my-topic --broker-list localhost:9092`

- CLI Producer with Key Value: `bin/kafka-console-producer.sh --topic my-topic --broker-list localhost:29092 --property "key.separator=:" --property "parse.key=true"`
Eg: k1:v1

- List consumer groups : `bin/kafka-consumer-groups --list --bootstrap-server localhost:9092`

- Describe consumer group: `bin/kafka-consumer-groups --describe --group group-id --bootstrap-server localhost:9092`

## Schema Registry Commands
- List all subjects: `curl -X GET http://localhost:8081/subjects`

- List all versions of schema: `curl -X GET http://localhost:8081/subjects/<subject-name>/versions`

- Delete a particular version of Schema: `curl -X DELETE http://localhost:8081/subjects/string_user_topic_dlq-value/versions/1`


# References
- https://gist.github.com/ursuad/e5b8542024a15e4db601f34906b30bb5
- https://docs.confluent.io/current/schema-registry/docs/using.html
- https://github.com/edenhill/kafkacat
