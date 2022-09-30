# Task List:
- Write a Producer with simple Serializer
- Write a Producer with complex Serializer
- Write a Producer with Kafka running on Confluent Cloud
- Testcases for the Producer
- Monitor Kafka Cluster and Clients


# Kafka Integration

## Envirionment Setup
- Confluent version used : 6.2.0
- Java version used : 11

## Docker Setup
- Navigate: `cd Kafka-Setup/Kafka-Docker-Setup`
- Run: `docker-compose up -d --build`
- Download Kafka Management Scripts: `https://www.apache.org/dyn/closer.cgi?path=/kafka/2.5.0/kafka-2.5.0-src.tgz`
- Compile the code from the root after unzipping the above file: `./gradlew jar -PscalaVersion=2.12.10`
- Bring Env up: `docker-compose up`
- Bring the env down: `docker-compose down`


## Automation Script
- Write script to spin up local kafka instance
- Write script to spin up local confluent kafka with schema registry instance
- Write script to:
    - Create topic
    - Delete records in topic
    - Delete topic
- Write script to create Java based project for Kafka
- Write script to create Java based project for Kafka Streams

