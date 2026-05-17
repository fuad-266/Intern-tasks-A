# Security Project

A Spring Boot application scaffolded with JWT-based authentication and role-based access control.

## Tech Stack

- Java 17
- Spring Boot 4.0.6
- Spring Security
- Spring Data JPA
- PostgreSQL
- JWT (jjwt 0.11.5)
- Lombok

## Project Structure

```
src/main/java/com/security/security_project/
├── config/         # App-level configuration beans
├── security/       # Spring Security configuration
├── jwt/            # JWT utility and filter
├── user/           # UserDetailsService implementation
├── auth/           # Auth request/response DTOs
├── role/           # Role-related logic
├── controller/     # REST controllers
├── service/        # Business logic
├── repository/     # Spring Data JPA repositories
└── entity/         # JPA entities
```

## Getting Started

1. Configure your PostgreSQL database in `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_db
spring.datasource.username=your_user
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

app.jwt.secret=your_secret_key
app.jwt.expiration-ms=86400000
```

2. Run the application:

```bash
./mvnw spring-boot:run
```
