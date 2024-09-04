# Project Overview
The Transaction service API allows users to manage accounts and transactions.It provides endpoints for creating accounts, retrieving account details, and recording transactions such as purchases, withdrawals, and credit vouchers.

# Features
- Create Account: Allows creating a new account with a unique document number.
- Retrieve Account Information: Allows fetching account details using the account ID.
- Create Transaction: Allows recording a transaction associated with an account.

# Technologies Used
- Java 17
- Spring Boot 3.x
- Spring Data JPA
- MySQL Database 
- Swagger OpenAPI 3 for API documentation
- JUnit 5 for testing

## Getting Started

### Prerequisite
- Install [Java17](https://www.oracle.com/in/java/technologies/downloads/)
- Install [Maven 3.6 or higher](https://maven.apache.org/install.html)
- Install [IntelliJ](https://www.jetbrains.com/idea/download/#section=mac) (preferred) or any other IDE

### Local Setup

- Clone the Repo : ``` git clone https://github.com/Deepika-786/transaction-routine.git```
- Pull the master branch
- Open project in IntelliJ required plugins (Intellij IDEA > Preferences > Plugins > Marketplace):
- Finally, to fetch all dependencies run:
    ```shell
    mvn clean install
    ```
- Run the application using : ```mvn spring-boot:run```
- Run the tests : ```mvn test```


## API Reference

[Swagger Doc](http://localhost:8080/swagger-ui/index.html)


## Running the Application with Docker

- Build the Docker image : ```docker build -t transaction-service:latest .```
- Run the docker container : ```docker run -p 8080:8080 transaction-service:latest```

## Contributing
Contributions are welcome! Please open an issue or submit a pull request if you have any improvements or bug fixes.


