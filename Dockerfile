## Use an official OpenJDK runtime as a parent image
#FROM openjdk:17-jdk-slim
#
## Set the working directory in the container
#WORKDIR /app
#
## Copy the packaged jar file into the container
#COPY target/Recommendation-Based-Telemedicine-0.0.1-SNAPSHOT.jar /app/app.jar
#
## Expose port 8080 to the outside world (change it if your app runs on a different port)
#EXPOSE 8080
#
## Run the jar file when the container starts
#ENTRYPOINT ["java", "-jar", "/app/app.jar"]
#



# Use the official Maven image to build the app
FROM maven:3.8.5-openjdk-17 AS build

# Set working directory in the container
WORKDIR /app

# Copy the pom.xml and download project dependencies
COPY pom.xml ./
RUN mvn dependency:go-offline

# Copy the entire project and build it
COPY . ./
RUN mvn clean package -DskipTests

# Use an OpenJDK image to run the app
FROM openjdk:17-jdk-alpine

# Set the working directory for the new image
WORKDIR /app

# Copy the built JAR from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Set the default command to run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
