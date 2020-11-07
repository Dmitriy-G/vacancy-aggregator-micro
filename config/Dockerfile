FROM openjdk:11

COPY /target/config-*.jar /config.jar

ENTRYPOINT ["java","-jar", "config.jar"]