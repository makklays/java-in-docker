# Use a Java base image
#FROM openjdk:17

# Set the working directory
#WORKDIR /

# Copy the application jar to the container
#COPY ./target/*.jar /usr/share/kuziv/java-in-docker.jar

#EXPOSE 8080
# Set the environment variables
#ENV JAVA_OPTS=""

# Define the command to run the application
#ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /usr/share/kuziv/java-in-docker.jar" ]

# for Kubernetes
FROM openjdk:17
COPY target/JavaInDocker-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

