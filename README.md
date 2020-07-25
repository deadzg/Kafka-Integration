# Kafka Integration

## Envirionment Setup
- Confluent version used : 5.5.0
- Java version used : 11

## Docker Setup
- Navigate: `cd Kafka-Setup/Kafka-Docker-Setup`
- Run: `docker-compose up -d --build`
- Download Kafka Management Scripts: `https://www.apache.org/dyn/closer.cgi?path=/kafka/2.5.0/kafka-2.5.0-src.tgz`
- Compile the code from the root after unzipping the above file: `./gradlew jar -PscalaVersion=2.12.10`
