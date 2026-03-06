# Intern Task 1 - Spring Boot Demo Application

A simple Spring Boot web application demonstrating basic REST API functionality.

## Overview

This is a demo Spring Boot application that provides a simple REST endpoint. It's built using Spring Boot 4.0.3 and Java 17, showcasing the fundamentals of creating a web service with Spring Framework.

## Features

- RESTful API endpoint
- Spring Boot Web starter integration
- Maven build configuration
- Basic controller implementation

## Prerequisites

- Java 17 or higher
- Maven 3.6+ (or use the included Maven wrapper)

## Getting Started

### Running the Application

You can run the application using the Maven wrapper (no Maven installation required):

**On Windows:**
```cmd
mvnw.cmd spring-boot:run
```

**On Linux/Mac:**
```bash
./mvnw spring-boot:run
```

Alternatively, if you have Maven installed:
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080` by default.

### Building the Application

To build the project and create a JAR file:

```bash
mvnw.cmd clean package
```

The JAR file will be created in the `target` directory.

### Running the JAR

After building, you can run the application using:

```bash
java -jar target/intern-task1-0.0.1-SNAPSHOT.jar
```

## API Endpoints

### GET /

Returns a simple greeting message.

**Request:**
```
GET http://localhost:8080/
```

**Response:**
```
Hello Spring Boot!
```

## Project Structure

```
intern-task1/
├── src/
│   ├── main/
│   │   ├── java/com/example/intern_task1/
│   │   │   ├── InternTask1Application.java    # Main application class
│   │   │   └── HelloController.java            # REST controller
│   │   └── resources/
│   │       └── application.properties          # Application configuration
│   └── test/
│       └── java/com/example/intern_task1/
│           └── InternTask1ApplicationTests.java
├── pom.xml                                      # Maven configuration
└── README.md
```

## Technologies Used

- **Spring Boot 4.0.3** - Application framework
- **Spring Web** - RESTful web services
- **Maven** - Build and dependency management
- **Java 17** - Programming language

## Testing

Run the tests using:

```bash
mvnw.cmd test
```

## Configuration

Application configuration can be modified in `src/main/resources/application.properties`.

To change the server port, add:
```properties
server.port=8081
```

## Development

This project uses Spring Boot's auto-configuration and embedded Tomcat server, making it easy to develop and deploy.

### Adding New Endpoints

Create new methods in `HelloController.java` or add new controller classes with `@RestController` annotation.

## License

This is a demo project for learning purposes.

## Author

Demo project for Spring Boot
