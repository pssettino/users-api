FROM openjdk:8 as Target
COPY build/libs/users-api-0.0.1.jar users-api.jar

ENTRYPOINT ["java","-jar","/users-api.jar"]

EXPOSE 9090
