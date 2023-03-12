FROM maven:3.6.3 AS maven

WORKDIR /usr/src/app
COPY . /usr/src/app

RUN mvn package

FROM adoptopenjdk/openjdk11:alpine-jre
WORKDIR /app

COPY --from=maven /usr/src/app/target/bredex_backend_test.jar /app/

EXPOSE 8080

ENTRYPOINT ["java","-jar", "bredex_backend_test.jar"]