FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=build /app/target/smart-tracking-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080

CMD ["sh", "-c", "export SPRING_DATASOURCE_URL=\"jdbc:postgresql://${DATABASE_URL#*@}\"; java -jar app.jar"]
