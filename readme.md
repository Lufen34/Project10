# OPENCLASSROOM LIBRARY

OL is a website/application allowing people to rent books from online. OL was made using Spring-Boot, Spring-data-Mongodb, Thymeleaf, Eureka, Feign, Spring cloud Gateway, Spring Mail, Spring Cloud Security, Spring Web, JWT - OAUTH.

## Prerequisites

* Mongodb need to be installed (For thoses who do not use docker.)

## Installation :

Everything is pre-configured in the Docker.

## Usage (Docker) :

Because of Github and Openclassroom filesize limitation you will have to download the image file here :

```
https://mega.nz/file/kZ0VBJaT#jnkKWtzueVknPyEJhmoh1LiHJKQen76fkfFCYShOgmY
```



import the docker container with the following command :

```bash
sudo tar -c . | docker import - project10
```



then in a terminal use the following command to connect to the docker :

```bash
sudo docker start openclassroom_library
```

then

```bash
sudo docker exec -it openclassroom_library bash
```

inside /home/Project7 use git pull in order to verify if project is updated.

How to deploy :

```bash
"Project10/<MICROSERVICE-YOU-WISH-TO-DEPLOY>/mvnw.cmd" package -f "Project10/<MICROSERVICE-YOU-WISH-TO-DEPLOY>/pom.xml"
```

The snapshot will be provided inside  ***/target/MICRO-SERVICE-NAME-0.0.1-SNAPSHOT.jar***, you'll just need to execute all of them using the command :

```bash
java -jar lade-0.0.1-SNAPSHOT.jar
```

Please make sure to execute them in the following order :

1. Config-service
2. Eureka-service
3. Gateway-service
4. OAuth-service
5. Book-service
6. Client (WEB)
7. Batch-service

***Make sure that each service has finished to initialize before running the next one.***

https://github.com/Lufen34/Project7-config-repo 

https://github.com/Lufen34/Project10/
