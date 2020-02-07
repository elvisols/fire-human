# Getting Started

This is a Java Spring boot application that stores, updates, retrieves, and deletes Person Entity. 

### Application Requirements:
- Java 8
- Maven 3+
- Node 8+
- Internet Connection

### Running the Application:
This application consist of the frontend - `./client/**` and the backend - `./src/**`.
There are two ways to running the application, as follows:
> Running as a backend/frontend application
```
~$ mvn clean
```
```
~$ mvn package
```
```
~$ mvn spring-boot:run
```
At this point your backend should be up and running else you may want to repeat the previous steps from the project source directory. 

Feel free to open a new terminal to proceed with the follow steps.
```
~$ cd ./client
```
```
~$ npm install
```
```
~$ npm start
```
Once started, our application will open up on a new browser.

> Running as a fullstack application
```
~$ cd ./client
```
```
~$ npm install
```
```
~$ npm run build
```
This will build your frontend into a build folder that maven will deploy.
```
~$ cd ..
```
```
~$ mvn clean
```
```
~$ mvn package
```
```
~$ mvn spring-boot:run
```
Great!, You should be able to access the application on `http://~:8899/` else you may need to repeat the steps above from the project source directory.

### Application Documentation
Once the application is successfully started ( - all tests passed), the documentation can be accessed from the project source directory via

`./target/generated-docs/index.html`

[Official Person Entity Documentation](./target/generated-docs/index.html)

which contains details about each endpoints and how to consume them.

### Assumptions
> The following port is available:
- 8899

### Demo
You can use the following account(s) to login or better still register one.

-- username: `jsmith@fire.com` | `sconnor@fire.com`

-- password: `password`

--
-

