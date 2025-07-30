# IW-2-BR

IW-2-BR is intended to be a study platform. 
A user can create an enviroment per subject. create and add notes, flashcards and exams to test their knowledge.
for more information visit the projects [wiki page](https://github.com/lvanrooijen/IW2BR/wiki)

## Requirements

- Java 17+
- Maven
- Docker

## How to run

1. Clone the project:

             https://github.com/lvanrooijen/IW2BR.git

2. `docker compose up --build`
3. `mvn install`
4. `mvn spring-boot:run`

   or push the button [![image](https://github.com/user-attachments/assets/c448a1c6-24f2-438a-9763-df3f5765c057)](https://www.youtube.com/watch?v=MtaTKXJ89jk)

## what runs where

* The API will be running on [8080](http://localhost:8080/)
* MailDev runs on [1080](http://localhost:1080/)
* PG admin runs on [5050](http://localhost:5050/) 
> _When registering a new server on pg admin you should use `IW2BR_postgres` as the hostname_
* The front end runs on [5173](http://http://localhost:5173/)

### links:

* [API documentation](http://localhost:8080/swagger-ui/index.html)
> _Backend needs to be running to access this resource_
