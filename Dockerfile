# Use OpenJDK 11 runtime as base image
FROM openjdk:11-jre-slim

# Set the working directory
WORKDIR /app

# Copy the jar file into the container
COPY build/libs/sample-java-project-V1.jar /app/sample-java-project.jar

# Run the jar file
CMD ["java", "-jar", "/app/sample-java-project.jar"]
