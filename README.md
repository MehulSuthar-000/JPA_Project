# Spring Data JPA Learning Project

A demonstration project created to explore and learn the fundamentals of Spring Data JPA, including entities, repositories, and relationships. This application sets up a backend service for a simple course management system.

## 📂 Project Structure

The project follows a standard Maven layout. Key packages include:

```
src
└── main
    ├── java
    │   └── com
    │       └── mehulsuthar
    │           └── JPA_Project
    │               ├── models
    │               │   ├── Author.java
    │               │   ├── Course.java
    │               │   ├── Lecture.java
    │               │   ├── Resources.java
    │               │   └── Section.java
    │               ├── repositories
    │               │   └── AuthorRepository.java
    │               └── JpaProjectApplication.java
    └── resources
        └── application.properties
```

-   **`models`**: Contains the JPA entity classes that map to database tables.
-   **`repositories`**: Holds the Spring Data JPA repository interfaces for database operations.
-   **`JpaProjectApplication.java`**: The main entry point for the Spring Boot application.
-   **`resources/application.properties`**: The file for all external configurations, including the database connection.

## 🛠️ Technologies Used

This project is built using a modern Java stack:

-   **Framework**: Spring Boot 3.5.3
-   **Language**: Java 21
-   **Data Persistence**: Spring Data JPA (with Hibernate)
-   **Database**: MySQL
-   **Web**: Spring Web
-   **Build Tool**: Apache Maven
-   **Utilities**: Lombok

## 🚀 Getting Started

Follow these instructions to get a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

Make sure you have the following software installed:

-   **Java Development Kit (JDK) 21** or later.
-   **Apache Maven**
-   **MySQL Server** running on your local machine or accessible on the network.

### Installation & Setup

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/MehulSuthar-000/JPA_Project.git](https://github.com/MehulSuthar-000/JPA_Project.git)
    ```

2.  **Navigate to the project directory:**
    ```bash
    cd JPA_Project
    ```

3.  **Create a MySQL Database:**
    -   Log in to your MySQL server.
    -   Create a new database for the project.
        ```sql
        CREATE DATABASE data_jpa;
        ```

4.  **Configure the Database Connection:**
    -   Open the `src/main/resources/application.properties` file (or `application.yml`).
    -   Add the following properties. These values are based on the project's configuration.

    ```properties
    # MySQL Database Connection
    spring.datasource.url=jdbc:mysql://localhost:3306/data_jpa
    spring.datasource.username=mehul
    spring.datasource.password=Mehul_2003
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

    # Hibernate Properties
    # 'create-drop': Drops the schema when the session factory is closed, typically when the application is stopped.
    # This is useful for testing as it cleans up the database after each run.
    spring.jpa.hibernate.ddl-auto=create-drop
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
    ```

5.  **Build the project:**
    -   Use Maven to compile the code and download dependencies.
    ```bash
    mvn clean install
    ```

## 🕹️ Running the Application

You can run the application directly using the Spring Boot Maven plugin:

```bash
mvn spring-boot:run
```

The application will start, and Spring Boot will automatically create the necessary tables in your database. Because `ddl-auto` is set to `create-drop`, the database tables will be **deleted** when the application stops.

## 🔌 API Endpoints

*(This section is a placeholder. As you build REST controllers, you can document them here.)*

Once you create `RestController` classes to expose your data, you can list the endpoints here.

**Example: Author API**

-   `GET /api/v1/authors`: Retrieves a list of all authors.
-   `POST /api/v1/authors`: Creates a new author.
