FROM openjdk:11

COPY /target/notification_sender-*.jar /notification_sender.jar

ENTRYPOINT ["java","-jar", "notification_sender.jar"]