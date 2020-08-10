Swagger
=======

You can open Swagger UI:
  - http://localhost:8080/swagger-ui/index.html

Or if you run the application in Docker, on port 8081:
  - http://localhost:8081/swagger-ui/index.html  

Some pages about Swagger:
  - https://medium.com/@hala3k/setting-up-swagger-3-with-spring-boot-2-a7c1c3151545
  - https://springfox.github.io/springfox/docs/snapshot/
  - https://www.springboottutorial.com/spring-boot-swagger-documentation-for-rest-services

Docker
======

Build a docker image:
  > docker build -t quiz-app .

Run the docker image on port 8081:
  > docker run -d -p 8081:8080 spring-boot-docker

List running containers:
  > docker ps

List all containers (even those are not running):
  > docker ps --all

Start an existing container:
  > docker start <container>

Stop a running container:
  > docker stop <container>

Stop a running container:
  > docker kill <container>

Delete a container:
  > docker rm <container> 
