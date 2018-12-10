# Dockerfile

# Debian baseimage with Java 11
FROM openjdk:11.0.1-slim-sid

#Adding the Jar file to Docker Container
ADD build/libs/docker-java11-springboot-0.1.0.jar docker-java11-springboot-0.1.0.jar

#Exposing port 8085 in Container
EXPOSE 8085

#Passing parameters on how to run the entrypoint
ENTRYPOINT ["java", "-jar", "docker-java11-springboot-0.1.0.jar"]