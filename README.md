# Multi Database Storage API

**Multi-Database Storage API** is a backend service built with **Java** and **Spring Boot** demonstrating how to perform CRUD operations on two different types of databases simultaneously: a relational database (**AWS RDS for MySQL**) and a NoSQL database (**AWS DynamoDB**). The API allows clients to choose the target database through distinct URL paths.

## 🚀 Features

- **Dual Database Integration**: Supports full CRUD operations for both AWS RDS (MySQL) and AWS DynamoDB in a single application.
- **RESTful API Design**: Provides parallel sets of endpoints for interacting with each database independently.
- **Standardized API Responses**: Uses a consistent wrapper for all API responses and includes global exception handling.
- **Profile-Based Configuration**: Includes `local` and `dev` profiles for easy switching between local development (with static credentials) and production deployment on EC2 (using IAM roles).
- **Secure AWS Integration**: Designed to use IAM roles for secure, key-less access to AWS services when deployed on EC2.
- **JPA & DynamoDB Enhanced Client**: Uses Spring Data JPA for RDS and the AWS SDK's DynamoDbEnhancedClient for DynamoDB.

## 🛠️ Tech Stack

### Backend:
- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Lombok

### Databases:
- AWS RDS for MySQL
- AWS DynamoDB

### Cloud Services:
- AWS SDK for Java 2.x

## 📂 Project Structure
```
multidbstore/
└── src/
    └── main/
        └── java/
            └── com/marvel/multidbstore/
                ├── config/           # Spring Boot Configuration
                ├── controller/       # API Controllers (RDS & DynamoDB)
                ├── entity/           # Data Models (RDS & DynamoDB)
                ├── repository/       # Data Access Layer
                ├── responsehandler/  # Global Exception & API Response Handling
                └── service/          # Business Logic
```

## 📦 Installation & Setup

### 1️⃣ Clone the Repository
```sh
git clone [https://github.com/utkarshanand07/MultiDBStore.git](https://github.com/utkarshanand07/MultiDBStore.git)
cd MultiDBStore
```

### 2️⃣ Configure AWS Services
This project uses Spring Profiles to manage database configurations.

#### For Local Development (`local` profile)
This profile uses static AWS keys for DynamoDB and a full connection string for RDS.
1.  In `src/main/resources/`, create an `application-local.properties` file.
2.  Add your AWS and RDS credentials:
    ```properties
    # AWS Credentials for DynamoDB
    cloud.aws.credentials.access-key=YOUR_AWS_ACCESS_KEY
    cloud.aws.credentials.secret-key=YOUR_AWS_SECRET_KEY
    cloud.aws.region.static=your-aws-region

    # AWS RDS (MySQL) Connection
    spring.datasource.url=jdbc:mysql://<your-rds-endpoint>/<database-name>
    spring.datasource.username=your-rds-username
    spring.datasource.password=your-rds-password
    rds.table.name=your_rds_product_table_name

    # JPA & Hibernate
    spring.jpa.hibernate.ddl-auto=update
    ```
3.  Activate the profile by setting `spring.profiles.active=local` in the main `application.properties` file.

#### For Production/Deployment (`dev` profile)
This profile is for deployment on an EC2 instance. It uses an IAM role for DynamoDB access, but still requires credentials for the RDS database.
1.  In `src/main/resources/`, create an `application-dev.properties` file.
2.  Add your region and RDS details:
    ```properties
    # AWS Region
    cloud.aws.region.static=your-aws-region

    # AWS RDS (MySQL) Connection
    spring.datasource.url=jdbc:mysql://<your-rds-endpoint>/<database-name>
    spring.datasource.username=your-rds-username
    spring.datasource.password=your-rds-password
    rds.table.name=your_rds_product_table_name

    # JPA & Hibernate
    spring.jpa.hibernate.ddl-auto=update
    ```
3.  Activate the profile by setting `spring.profiles.active=dev` before deployment.

### 3️⃣ Run the Application
You can run the application using the Maven wrapper:
```sh
# Make sure you have activated the desired profile
./mvnw spring-boot:run
```
The server will start on the default port (usually 8080).

## 📜 API Endpoints

### AWS RDS (MySQL) Endpoints
Base Path: `/rds-products`
| Method | Endpoint | Description |
|---|---|---|
| POST | `/product` | Creates a new product in RDS. |
| GET | `/{id}` | Fetches a product by its Long ID from RDS. |
| GET | `/products` | Fetches all products from RDS. |
| PUT | `/product` | Updates an existing product in RDS. |
| DELETE | `/{id}` | Deletes a product by its Long ID from RDS. |
| DELETE | `/clear` | Deletes all products from the RDS table. |

### AWS DynamoDB Endpoints
Base Path: `/dynamodb-products`
| Method | Endpoint | Description |
|---|---|---|
| POST | `/product` | Creates a new product in DynamoDB. |
| GET | `/{id}` | Fetches a product by its String ID from DynamoDB. |
| GET | `/products` | Fetches all products from DynamoDB. |
| PUT | `/product` | Updates an existing product in DynamoDB. |
| DELETE | `/{id}` | Deletes a product by its String ID from DynamoDB. |
| DELETE | `/clear` | Deletes all products from the DynamoDB table. |


## 🚀 Deployment
1.  **Set up AWS**: Create an RDS (MySQL) database instance and a DynamoDB table (e.g., `inventory`).
2.  **Create an IAM Role**: Create a role for your EC2 instance with permissions to access DynamoDB (`AmazonDynamoDBFullAccess` or a more restricted policy).
3.  **Build the project**: `./mvnw clean package`.
4.  **Deploy to EC2**: Deploy the generated JAR file to your EC2 instance and run it with the `dev` profile activated.

## 🐾 Connect With Us
- **Project Link:** [MultiDBStore on GitHub](https://github.com/utkarshanand07/MultiDBStore)
- **Author:** [Utkarsh Anand](https://github.com/utkarshanand07)

---

⭐ **If you like this project, consider giving it a star!** ⭐
