# Vaihe 1: rakenna projekti Mavenilla
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -Pproduction -DskipTests

# Vaihe 2: aja valmis JAR kevyemmässä ympäristössä
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar","--spring.profiles.active=docker"]
