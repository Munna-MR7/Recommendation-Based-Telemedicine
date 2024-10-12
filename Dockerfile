# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged jar file into the container
COPY target/Recommendation-Based-Telemedicine-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose port 8080 to the outside world (change it if your app runs on a different port)
EXPOSE 8080

# Run the jar file when the container starts
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
