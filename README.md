# SUBSCRIPTIONS CYCLES PROJECT
---

## You need at least the following tools during application development:

- Java JDK (21 or newer)
- Maven (3.5 or newer)
- A Java IDE (IntelliJ IDEA, Eclipse EE, NetBeans IDE, VS Code, etc.)

## DBMS
- To run the application is needed to install PostgreSQL DB.
- Create database `subscription_cycles`.
- Create a user according `application-{profile}.properties` file and give access to the database.

## Running the Application locally
- The default application profile is `dev`(available profiles are `dev` and `prod`).
- Execute the mvn command: `mvn spring-boot:run`

- You can also start by running `com/lufthansa/subscriptions/Application.java`.

**In either case, will be accessible from** http://localhost:8080.

## Swagger docs

### 1.  REST API UI

- For local dev profile, you can find the swagger UI with all endpoints in: http://localhost:8080/swagger-ui

### 2.  REST API definition

- The REST API definitions (in JSON format) are available at: http://localhost:8080/rest-api