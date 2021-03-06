# docker-java11-springboot
A tutorial on how to setup Docker on a simple Spring Boot 2 application written in Java 11 and build with Gradle 4.10.
If you don't know how to start up a Spring Boot project, I suggest cloning this project. But if you want to learn how to set up a Spring Boot project from the start, then go to https://spring.io/guides/gs/spring-boot/ (note: that tutorial is in Java 8).

If you want to setup Docker For a Linux OS or on a virtual machine for Linux Ubuntu or Linux Red Hat Enterprise, then you can follow my earlier walkthroughs here:

    https://github.com/robinstrom/docker-ee-linuxredhatenterprise7

    https://github.com/robinstrom/docker-ce-linuxubuntu18.04
    
I am using a Windows computer for this project, so I will be using Docker for Windows, which can be downloaded here:

    https://store.docker.com/editions/community/docker-ce-desktop-windows
    
During the installation, simply choose everything that is default.

You have to have admin access for the following step:

Since Docker is using Hyper-V for virtualization, be sure that it is enabled. This is usually fixed during Docker installation, but if not,
follow the steps below. FYI, this will disable your Oracle VirtualBox, but will not delete any machines.
To enable Hyper-V, simply put "Hyper-V" in your searchbar which appears after pressing the search button in the left-bottom corner 
and select: Turn Windows features on or off.
Here you check that the Hyper-V checkbox is marked, if not, then do it now. Then Restart your computer.


Creating the Dockerfile:

To have a project running in a Docker container, we need a Dockerfile. So after you've created your Spring Boot project, create a file called Dockerfile in your project folder.

If we don't want to write everything from scratch, there are a lot of great Base-Images on DockerHub (https://hub.docker.com/).
For this project, we want a base-image with an OS that is able to run Java 11. OpenJDK has a lot of different Images to choose from uploaded on Docker Hub,
so here we will use a slim version of Linux Debian, which already have Java 11 included:

    FROM openjdk:11.0.1-slim-sid

Next is adding your JAR-file:

    ADD build/libs/docker-java11-springboot-0.1.0.jar docker-java11-springboot-0.1.0.jar

Opening a port of your choice:

    EXPOSE 8085

Setting the command and parameters that will be executed first when the container is running:

    ENTRYPOINT ["java", "-jar", "docker-java11-springboot-0.1.0.jar"]


Building and Running you Docker Image:

Open up your Command Prompt as Administrator and change directory in to your project folder:

    cd path/to/project/
    
Create and build a Docker Image of your Dockerfile and give it a name (here I name it docker-java11):

    docker build -f Dockerfile -t docker-java11 .

If everything has gone as it's supposed to, then you can start up a Container based on the Image by typing:

    docker run -p 8085:8085 docker-java11
    
This command will run our project on localhost at port 8085, using our newly created Image "docker-java11".
The project and Image is both pointing to port 8085.

To be able to SSH into your running container, you need the Containers ID. To get information about the running containers, write:

    docker ps


Still as an administrator, SSH into your running Container and starting bash inside it:

    docker exec -it <CONTAINER ID> /bin/bash
    
*** Be aware of that the structure of the OS is different depending on what OS you have, so for example, if you're using an Alpine base image, then you can open bash in the container by writing:

    docker exec -it <CONTAINER ID> /bin/sh
    
You are now logged in as root inside of your Container.


To be able to watch and save the container logs in the host, you have to create a shared folder:

Create folder on host:

    mkdir container-logs
    
Now run the container and create a volume, pointing to the newly created folder on your local : folder in container:

    docker run -it -p 8085:8085 -v /absolute/path/to/newfolder/container-logs:/logs docker-java11
    
If you just want to check the logs quickly without setting up a volume:

    docker logs <CONTAINER ID>
