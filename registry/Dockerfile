FROM openjdk:11

COPY /target/registry-*.jar /registry.jar

ENTRYPOINT ["java","-jar", "registry.jar"]