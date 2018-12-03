# Dockerfile

# Baseimage for Oracle java 11

FROM oraclelinux:7-slim

#Install Gradle
RUN wget -q https://services.gradle.org/distributions/gradle-4.10.1-bin.zip \
    && unzip gradle-4.10.1-bin.zip -d /opt \
    && rm gradle-4.10.1-bin.zip

# Set Gradle in the environment variables
ENV GRADLE_HOME /opt/gradle-4.10.1
ENV PATH $PATH:/opt/gradle-4.10.1/bin