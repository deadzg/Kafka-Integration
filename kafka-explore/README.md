## Consume Message using Kafka CLI
sh /Users/smalwe/Tools/kafka-2.5.0-src/bin/kafka-console-consumer.sh --bootstrap-server localhost:29092 --topic order 

## Generate avro class
java -jar avro-tools-1.10.2.jar compile schema user.avsc .

## References
- https://www.baeldung.com/lombok-ide
- https://www.baeldung.com/java-random-string
- https://stackoverflow.com/questions/9424364/cant-compile-project-when-im-using-lombok-under-intellij-idea
- https://docs.gradle.org/current/userguide/declaring_repositories.html
- https://sagioto.github.io/maven2gradle/
- Download Avro Tools: https://mvnrepository.com/artifact/org.apache.avro/avro-tools/1.10.2
