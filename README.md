# inventory-management-system

Overview :
This project involves the design and implementation of a Java-based backend system with MySQL integration using an Object-Relational Mapping (ORM) framework like Hibernate for database operations,
which provides a RESTful API for managing products and categories.
The API supports CRUD operations (Create, Read, Update, Delete) for both products and categories.
This application is designed a MySQL database schema with two main tables - "Products" and "Categories," and then implement a Java RESTful API using Spring Boot. 

Technology Stack:
1.Java
2.Spring Boot
3.MySQL

To set up and run the project on your local machine, follow these steps:
1.Clone this repository.
2.Configure the MySQL database connection in the application properties.
3.Build and run the Spring Boot application.
4.Access the API endpoints using a tool like Postman or any web browser.

The following API endpoints are available for interacting with the system:

GET: /products - Retrieve a list of all products.
GET: /products/{id} - Retrieve a specific product by its ID.
POST: /products - Add a new product.
PUT: /products/{id} - Update the details of an existing product.
DELETE: /products/{id} - Delete a product.
Similarly, corresponding endpoints created for categories to manage CRUD operations.
