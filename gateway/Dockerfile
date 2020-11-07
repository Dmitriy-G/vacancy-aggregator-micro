FROM openjdk:11

COPY /target/gateway-*.jar /gateway.jar

ENTRYPOINT ["java","-jar", "gateway.jar"]

EXPOSE 8761