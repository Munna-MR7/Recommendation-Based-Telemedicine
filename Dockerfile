## Use the official Maven image to build the app
#FROM maven:3.8.5-openjdk-17 AS build
#
#COPY . .
#
#RUN mvn clean package -DskipTests
#
## Use an OpenJDK image to run the app
#FROM openjdk:17.0.1-jdk-slim
#
## Copy the built JAR from the previous stage
#COPY --from=build /target/Recommendation-Based-Telemedicine-0.0.1-SNAPSHOT.jar Recommendation-Based-Telemedicine.jar
#
## Expose the application port
#EXPOSE 8080
#
## Set the default command to run the jar file
#ENTRYPOINT ["java", "-jar", "Recommendation-Based-Telemedicine.jar"]
