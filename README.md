<img alt="spotlight logo" src="./images/spotlightLogo2.svg" width="80" />

### spotlight-api
An application for your application.

One of the most tedious processes of software development is documentation. 
Not only the processes, but the endpoints as well. Spotlight offers a quick
and easy solution to provide your users with great looking documentation that
is easy to understand.

### setup:
A database should be set up for this API. It's recommended that you use mysql.
You may use any database that is compatible with liquibase.

The following can be configured in the application.properties file

Properties:
  - server.port: which port you'd like the API to run on.
  - spring.datasource.url: where the database is located & which to use
  - spring.datasource.username: login for the service
  - spring.datasource.password: password for the service

### build / run:
From the project's root directory run the following commands:

```
./gradlew bootJar && java -jar build/libs/API-Documenter-0.0.1.jar
```