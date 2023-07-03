#Build stage
FROM gradle:7.5.1-jdk17-alpine AS BUILD
WORKDIR /usr/app/
COPY . .
RUN gradle build

# Package stage

FROM openjdk:17
ENV JAR_NAME=santanderapp-0.0.1-SNAPSHOT.jar
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY --from=BUILD $APP_HOME .
EXPOSE 8080
ENTRYPOINT exec java -jar $APP_HOME/build/libs/$JAR_NAME
