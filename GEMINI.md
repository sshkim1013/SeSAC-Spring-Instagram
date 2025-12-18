# Project Overview

This is a Spring Boot project that appears to be a web application similar to Instagram. It's built with Java 21 and uses Gradle for dependency management.

**Key Technologies:**

*   **Backend:** Spring Boot, Spring Security, Spring Data JPA
*   **Frontend:** Thymeleaf
*   **Database:** MySQL
*   **Build:** Gradle

**Architecture:**

The project follows a standard Spring Boot project structure.
*   `src/main/java`: Contains the Java source code.
    *   `com.example.instagram`: The main package.
        *   `config`: Spring Security and Web configurations.
        *   `controller`: Handles web requests.
        *   `dto`: Data Transfer Objects for requests and responses.
        *   `entity`: JPA entities representing the database schema.
        *   `exception`: Custom exception handling.
        *   `repository`: Spring Data JPA repositories for database access.
        *   `security`: Custom user details for Spring Security.
        *   `service`: Business logic.
*   `src/main/resources`: Contains application resources.
    *   `application.properties`: Configuration file for the application (e.g., database connection).
    *   `static`: Static assets (CSS, JavaScript, images).
    *   `templates`: Thymeleaf templates for the UI.

# Building and Running

**Build the project:**
```bash
./gradlew build
```

**Run the application:**
```bash
./gradlew bootRun
```

**Run tests:**
```bash
./gradlew test
```

# Development Conventions

*   The project uses Lombok to reduce boilerplate code.
*   It follows the standard Spring Boot conventions for structuring code.
*   The project uses `thymeleaf-layout-dialect` for layout management in the frontend.
