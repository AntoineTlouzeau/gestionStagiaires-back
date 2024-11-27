# Gestion Backend Project

Gestion Backend is a Java-based project that provides backend services for managing teams, skills, and managers. This project is designed to handle API requests, perform filtering operations, and manage data using Spring Boot and JPA (Java Persistence API).

## Table of Contents
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Running the Application](#running-the-application)
- [Contributing](#contributing)
- [License](#license)

## Technologies Used

- Java 11
- Spring Boot
- Spring Data JPA
- Swagger/OpenAPI for API Documentation
- Lombok for code simplification
- MySQL Database (can be replaced with other databases)
- Maven for dependency management and build
- Git for version control

## Project Structure

The project follows a standard Spring Boot application structure:
<pre>
gestion-backend/
├── src/main/java/
│ ├── com.insy2s.gestionbackend/
│ │ ├── controller/
│ │ ├── dto/
│ │ ├── dto/mapper/
│ │ ├── errors/
│ │ ├── filter/
│ │ ├── model/
│ │ ├── repository/
│ │ └── service/
│ ├── resources/
│ └── test/
└── pom.xml
</pre>


- `controller`: Contains the REST controllers that define API endpoints.
- `dto`: Contains Data Transfer Object (DTO) classes used for API responses and requests.
- `dto/mapper`: Contains mapper classes responsible for converting entities to DTOs and vice versa.
- `errors`: Contains custom exception classes for error handling.
- `filter`: Contains classes with specifications for filtering data using Spring Data JPA.
- `model`: Contains entity classes representing the database tables.
- `repository`: Contains Spring Data JPA repositories for database interactions.
- `service`: Contains service classes that handle business logic.

## Getting Started

To get started with the Gestion Backend project, follow these steps:

1. Clone the repository:
```bash
git clone https://github.com/your-username/gestion-backend.git
```
2. Install the required dependencies using Maven:
Install the required dependencies using Maven:
```bash
cd gestion-backend
mvn clean install
```
- Set up the MySQL database with the required tables and credentials.
- Update the database configuration in application.properties located in the resources directory.
- Build the application:
```bash
mvn spring-boot:run
```

## Running the Application
- Run the application:
```bash
mvn spring-boot:run
```
- The application will start running at http://localhost:8080.
- You can view the API documentation at http://localhost:8080/swagger-ui.html.
- You can also view the API documentation in JSON format at http://localhost:8080/v2/api-docs.
- You can view the H2 database console at http://localhost:8080/h2-console.

## API Endpoints

The following API endpoints are available:
The Gestion Backend project exposes several API endpoints for managing teams, skills, and managers. These endpoints are documented using Swagger/OpenAPI, and you can access the API documentation at http://localhost:8080/swagger-ui.html once the application is running.

## Contributing
Contributions to the Gestion Backend project are welcome! If you find any issues or have suggestions for improvements, please create a new issue or submit a pull request.

## License
The Gestion Backend project is open-source and available under the MIT License.
