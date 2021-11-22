FROM amazoncorretto:11-alpine-jdk
ADD target/user-task-0.0.1-SNAPSHOT.jar user-task-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","user-task-0.0.1-SNAPSHOT.jar"]
