# Getting Started

This is a simple (Java) Spring boot application that stores, updates, retrieves, and deletes Person Entity. 

### Application Requirements:
- Java 8
- Maven 3+
- Internet Connection

### Application Deployment:
Follow the steps below, from the project source, to run the application.
```
~$ mvn clean
```
```
~$ mvn package
```
```
~$ mvn spring-boot:run
```
### Application Documentation
Once the application is successfully started, the documentation can be accessed from the project source via

`./target/generated-docs/index.html`

[Official Person API documentation](./target/generated-docs/index.html)

which contains details about each endpoints and how to consume them.

### Assumptions
> The following port is available:
- 8899

