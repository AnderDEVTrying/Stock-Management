# Stock-Management
# Stock Management System

This is a Spring Boot application for managing stock in a store. It allows users to manage products, place orders, and track stock availability.

## Technologies Used
- Java
- Spring Boot
- Hibernate (JPA)
- H2 Database (for development)
- RESTful API

## Setup Instructions
1. Clone the repository.
2. Open the project in your favorite IDE (e.g., IntelliJ IDEA, Eclipse).
3. Build the project using Maven or Gradle.
4. Run the application.
5. Access the API endpoints using a tool like Postman or a web browser.

## Endpoints

### Products
- `POST /stock/product`: Add a new product to the inventory.
- `GET /stock/product`: Retrieve a list of all products.
- `PUT /stock/product`: Update an existing product.
- `DELETE /stock/product`: Delete a product from the inventory.

### Orders
- `POST /stock/order`: Place a new order.
- `GET /stock/order`: Retrieve a list of all orders.

## Usage
- Use the provided endpoints to manage products and place orders.
- Ensure that the necessary data (products, orders) is available in the database.


## License
This project is licensed under the [MIT License](LICENSE).
