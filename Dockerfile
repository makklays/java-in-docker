# Use a Java base image
FROM openjdk:11

# Set the working directory
WORKDIR /

# Copy the application jar to the container
COPY target/javaindocker-0.0.1-SNAPSHOT.war .
EXPOSE 8080
# Set the environment variables
ENV JAVA_OPTS=""

# Define the command to run the application
CMD ["java", "-jar", "crm-0.0.1-SNAPSHOT.war"]
