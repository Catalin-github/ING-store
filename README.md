ING Store - API

Project Overview
The ING Store Management Tool is a backend application designed to manage products, users, and roles for a store management system. It provides APIs to handle basic CRUD operations, authentication, and role-based access control using JSON Web Tokens (JWT). The application follows best practices in structuring Spring Boot projects, including dependency injection, DTOs, error handling, logging, and unit testing. The project also utilizes MySQL as the database and kafka.

Features
Product Management:
Add, update, delete, and view products.

User Management:
Handle user authentication and role-based access control.
Supports user registration and login with JWT authentication.

Role Management:
Roles are predefined in the system and assigned to users for role-based access control.

Authentication:
JWT-based authentication for securing API endpoints.
Login, logout, and protected resources.

Error Handling:
Global exception handling using @ControllerAdvice for consistent API responses.

Unit Tests:
-Unit tests for repositories and services.
-Integration tests for controllers to ensure robust functionality.
-Technology Stack
-Java 17 (Java 9+ features are utilized)
-Spring Boot 3.x
-Spring Security (JWT Authentication)
-Spring Data JPA (Database interactions)
-Hibernate (ORM)
-MySQL (Database)
-JUnit 5 (Unit testing framework)
-Maven (Build tool)
-Prerequisites
-Java 17+
-MySQL Database
-Maven


Installation and Setup

Clone the repository:
git clone https://github.com/Catalin-github/ing-store-management-tool.git
cd ing-store-management-tool
Configure the MySQL database:

Create a database named ing-store and update the application.properties file with your MySQL credentials:
spring.datasource.url=jdbc:mysql://localhost:3306/ing-store
spring.datasource.username=root
spring.datasource.password=

Install the necessary dependencies:
mvn clean install

Run the application:
mvn spring-boot:run
The API will be available at: http://localhost:8088

API Endpoints

Authentication & Authorization
POST /api/auth/login: User login to obtain a JWT token.
POST /api/auth/logout: User logout.

Product Management
GET /api/products: Get a list of all products.
POST /api/products: Add a new product.
PUT /api/products/{id}: Update an existing product.
DELETE /api/products/{id}: Delete a product.

User Management
GET /api/users: Get a list of all users (requires admin role).
POST /api/users: Create a new user (requires admin role).

Role Management
GET /api/roles: Get a list of all roles.
POST /api/roles: Create a new role.
