FROM openjdk:14-alpine
COPY build/libs/kafka-micronaut-app-*-all.jar kafka-micronaut-app.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "kafka-micronaut-app.jar"]