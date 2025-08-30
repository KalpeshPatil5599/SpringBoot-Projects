# Movie Management API

A Spring Boot RESTful API for managing movies, supporting CRUD operations with pagination and validation.

## Features
- Add, update, delete, and fetch movies
- Pagination for movie listing
- Input validation for movie fields
- JPA/Hibernate integration
- Unit tests for controller and service layers

## Endpoints
- `POST /movies` - Create a new movie
- `GET /movies` - List all movies (with `page` and `size` params)
- `GET /movies/{id}` - Get a movie by ID
- `POST /movies/{id}` - Update a movie by ID
- `DELETE /movies/{id}` - Delete a movie by ID

## Getting Started
1. **Clone the repository**
2. **Configure your database** in `src/main/resources/application.properties`
3. **Build and run**:
   ```sh
   ./mvnw spring-boot:run
   ```
4. **Run tests**:
   ```sh
   ./mvnw test
   ```

## Project Structure
- `controller/` - REST controllers
- `service/` - Business logic
- `dao/` - Data access layer
- `model/` - JPA entities
- `repo/` - Spring Data JPA repositories
- `test/` - Unit tests

## Requirements
- Java 17 or higher
- Maven 3.6+
- MySQL or compatible database

## License
MIT
