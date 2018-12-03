# springboot-java11-docker
A tutorial on how to setup Docker with a simple Spring Boot 2 application written in Java 11 and build with Gradle 4.10.

Install Docker on server

    install required dependencies:
    $ sudo apt install apt-transport-https ca-certificates curl software-properties-common
    
    import repositories GPG key
    $ curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
    
    add Docker APT repository
    $ sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
    
    update apt package list and install latest version of docker ce (community edition)
    $ sudo apt update
    $ sudo apt install docker-ce


Check version

    $ docker --version

Create Docker group

    $ sudo groupadd docker

Add user to docker group:
    
    $ sudo usermod -aG docker $USER
Log out and back in so that group membership is revaluated


Verify that docker runs without sudo command by running the hello world image:
$ docker run hello-world




Troubleshooting:

If Docker Daemon won’t start for some reason and you have to start it manually, type the command:
    
    $ dockerd
# docker-java-11-springboot
