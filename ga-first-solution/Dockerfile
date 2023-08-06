FROM maven:latest AS builder
WORKDIR /app
COPY pom.xml /app
COPY ga-first-solution-api/pom.xml /app/ga-first-solution-api/pom.xml
COPY ga-first-solution-api/src /app/ga-first-solution-api/src
COPY ga-first-solution-application/pom.xml /app/ga-first-solution-application/pom.xml
COPY ga-first-solution-application/src /app/ga-first-solution-application/src
COPY ga-first-solution-domain/pom.xml /app/ga-first-solution-domain/pom.xml
COPY ga-first-solution-domain/src /app/ga-first-solution-domain/src
COPY ga-first-solution-util/pom.xml /app/ga-first-solution-util/pom.xml
COPY ga-first-solution-util/src /app/ga-first-solution-util/src
RUN mvn clean install -DskipTests -Dmaven.multiModuleProjectDirectory=/app

FROM openjdk:19 as run
COPY --from=builder /app/ga-first-solution-application/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]