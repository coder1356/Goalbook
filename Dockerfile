FROM openjdk:17
ADD target/spe-0.0.1-SNAPSHOT.jar spe-0.0.1-SNAPSHOT.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar" , "spe-0.0.1-SNAPSHOT.jar"]

