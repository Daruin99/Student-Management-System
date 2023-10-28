# Student-Management-System
Project Overview
----------------

The Student Management System is a web application built using Spring Boot. It's designed to manage students and their associated teachers. The application allows teachers to log in and perform basic CRUD (Create, Read, Update, Delete) operations on their students.

Key Features
------------

-   **Entities**: The system revolves around two main entities: `Teacher` and `Student`. These entities have a one-to-many relationship, where one teacher can have multiple students.
-   **Spring Data JPA and Hibernate**: These technologies are used for data persistence. Hibernate's automatic schema generation is enabled in the `application.properties` file.
-   **MySQL**: This is the database used for storing all the data.
-   **Spring Security**: This is used for authentication and registration. It ensures that only authenticated users can perform certain operations.
-   **Password Strength and Encryption**: The system enforces a strong password policy to ensure the security of user accounts. Passwords are encrypted using Bcrypt before being stored in the database, adding an extra layer of security.
-   **Spring MVC and Thymeleaf**: These are used for handling web requests and rendering views.
-   **Unit Testing**: The repositories and service layer are thoroughly tested using JUnit, Mockito, and AssertJ. An H2 in-memory database is used for testing.

Setup
-----

To set up this project on your local machine, follow these steps:

1.  Clone the repository: Use the command `git clone <repository-url>` to clone the repository to your local machine.

2.  Install dependencies: Navigate to the project directory and use the command `mvn install` to install all necessary dependencies.

3.  Configure MySQL database: Create a new MySQL database and update the `application.properties` file with your database name, username, and password.

4.  Run the application: Use the command `mvn spring-boot:run` to start the application.

5.  Access the application: Open your web browser and navigate to `http://localhost:8080` to access the application.

Please note that you need to have Java Development Kit (JDK), Maven, and MySQL installed on your machine before you can run this project.

Testing
-------

To run tests, use the command `mvn test`.
