# IW-2-BR

IW-2-BR is intended to be a study platform.
For more information on the project and it's features visit the projects [Wiki Pages](https://github.com/lvanrooijen/IW2BR/wiki)

## Requirements

- Java 17+
- Maven
- Docker

## How to run

Clone the project:

             https://github.com/lvanrooijen/IW2BR.git

Build containers:</br>
`cd IW2BR`</br>
`docker compose up --build` </br></br>
Run backend: </br>
`mvn install` </br>
`mvn spring-boot:run`
<a href="https://www.youtube.com/watch?v=MtaTKXJ89jk" target="_blank">
<img
src="https://github.com/user-attachments/assets/e2e07ab6-bfc0-4ee8-99cb-d595f129b9ba"
alt="play button"
width="21"
height="21"
/>
</a> </br></br>
Run frontend end: </br>
`cd frontend` </br>
`npm install` </br>
`npm run forest`

## what runs where

* The API will be running on [8080](http://localhost:8080/)
* MailDev runs on [1080](http://localhost:1080/)
* PG admin runs on [5050](http://localhost:5050/)

> _When registering a new server on pg admin you should use `IW2BR_postgres` as the hostname_

* The front end runs on [5173](http://http://localhost:5173/)

### links:

* [API documentation](http://localhost:8080/swagger-ui/index.html)

> _Backend needs to be running to access this resource_





