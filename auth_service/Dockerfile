FROM openjdk:11

COPY /target/auth_service-*.jar /auth_service.jar

ENTRYPOINT ["java","-jar", "auth_service.jar"]