# Demo Management

The demo management service is...

This service can manage following features

- swagger
- actuator
- crud operation
- exception handling
- date formatter

## ER Diagram

[<img src="er_diagram.jpeg" width="700"/>](er_diagram.jpeg)

## Prerequisites

Before using this project, ensure you have the following:

- Java 17
- MySQL 8
- Apache Maven 3.8.5
- Docker

## How to use

1. Clone this repository to your local machine.

    ```bash
      git clone <repo link>
      cd <repo name>
    ```

2. Add env variables to `application-dev.properties` and set the values

3. Install Maven dependency

    ```
        mvn clean package -DskipTests
    ```

4. Run the `*.jar` file

    ```
        -jar <application_name-version>.jar
    ```

## API Documentation

- Swagger UI -> http://localhost:8000/swagger-ui/index.htm
- OpenAPI Docs -> http://localhost:8000/v3/api-docs

## Deploy on Docker

### Prerequisites

- Docker
- Docker compose
- Make

1. Create `.env.prod` file using `.env.example` file and set values
2. Make sure `SPRING_PROFILES_ACTIVE` is `prod`
3. Build and initiate docker containers using a make command
    ```
        make up_all
    ```
4. for next deployment use make commands
   ``` 
      make up_demo
      make down_demo
      make restart_demo
   ```

## Tech Stack

- Spring boot
- Docker
- MySQL

## Authors

If you have any questions or need further assistance, you can reach out to

- [Chanaka Dushmantha](https://github.com/dushmanthasse)
