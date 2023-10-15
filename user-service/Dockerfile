FROM openjdk:latest
EXPOSE 8081
CMD mvn clean install
ARG JAR_FILE=target/user-service-0.1.jar
ADD ${JAR_FILE} user-service-0.1.jar
ENTRYPOINT ["java","-jar","/user-service-0.1.jar"]
