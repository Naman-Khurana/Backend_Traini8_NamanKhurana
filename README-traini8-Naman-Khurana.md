# Training Center Management System

## 📑 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [Setup & Installation](#-setup--installation)
- [API Endpoints](#-api-endpoints)
- [Validation & Error Handling](#-validation--error-handling)
- [Development Guidelines](#-development-guidelines)
- [Contact](#-contact)

## 📌 Overview

The **Training Center Management System** is a Spring Boot REST API for managing training centers, their locations, and the courses they offer. It ensures **data integrity, validation, and efficient error handling** while following best practices in Spring Boot development.

## 🚀 Features

### **Core Functionality**

- **Training Center Management**: Create, retrieve, and filter training centers.
- **Address Management**: Structured address storage with validation.
- **Course Management**: Associate multiple courses per training center.
- **Filtering & Search**: Search centers by name, city, state, or capacity range.

### **Advanced Features**

- **Custom Validation**: Unique center code enforcement and input validation.
- **Error Handling**: Centralized exception handling with detailed error responses.
- **Logging**: Uses SLF4J & Logback for efficient logging.

## 🛠 Tech Stack

- **Backend**: Java 23, Spring Boot, Spring Data JPA, Hibernate
- **Database**: MySQL 8.0+
- **Build Tools**: Maven
- **Logging**: SLF4J, Logback
- **Other**: Lombok, Spring Validation

## 📂 Project Structure

```
com.namankhurana.springboot.traini8/
├── controller/          # REST Controllers
├── service/             # Business Logic Layer
├── dao/                 # Data Access Layer (Repositories)
├── dto/                 # Data Transfer Objects
├── entity/              # JPA Entities
├── exception/           # Custom Exceptions
├── exceptionHandler/    # Global Exception Handling
├── validator/           # Custom Validators
└── Traini8Application.java
```

## ⚙️ Setup & Installation

### **1️⃣ Prerequisites**

Ensure you have the following installed:

- **JDK 23** → [Download JDK](https://jdk.java.net/23/)
- **MySQL 8.0+** → [Download MySQL](https://dev.mysql.com/downloads/installer/)
- **Maven** → [Download Maven](https://maven.apache.org/download.cgi)
- **IntelliJ IDEA (Recommended)** → [Download IntelliJ](https://www.jetbrains.com/idea/)

### **2️⃣ Database Setup**

- Open MySQL and create a database:

```sql
CREATE DATABASE traini8database;
```

- Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/traini8database
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### **3️⃣ Build and Run the Project**

#### **Clone Repository**

```bash
git clone [repository-url]
cd traini8
```

#### **Build the Project**

```bash
./mvnw clean install   # Linux/macOS
mvnw.cmd clean install # Windows
```

#### **Run the Application**

```bash
./mvnw spring-boot:run   # Linux/macOS
mvnw.cmd spring-boot:run # Windows
```

## 📖 API Endpoints

### **1️⃣ Create a New Training Center**

```http
POST /api/trainingCenter/add
```

**Request Body:**

```json
{
  "centerName": "Tech Excellence Center",
  "centerCode": "TEC123456789",
  "address": {
    "detailedAddress": "123 Tech Park, Sector 1",
    "city": "Bangalore",
    "state": "Karnataka",
    "pincode": "560001"
  },
  "studentCapacity": 200,
  "contactEmail": "contact@techexcellence.com",
  "contactPhone": "9876543210",
  "courses": [
    { "course": "Advanced Java" },
    { "course": "Spring Framework" }
  ]
}
```

### **2️⃣ Get All Training Centers**

```http
GET /api/trainingCenter/getAll
```

### **3️⃣ Get Filtered Training Centers**

```http
GET /api/trainingCenter/getFiltered?city=Bangalore&minCapacity=100&maxCapacity=300
```

## 🛡️ Validation & Error Handling

### **Custom Validation Rules**

- **Center Code**: Must be exactly 12 characters and unique.
- **Email & Phone**: Must follow proper formats.
- **Pincode**: Must be a valid 6-digit number.

### **Global Exception Handling**

Handled in `GlobalExceptionHandler.java`:

```java
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ValidationErrorResponseDTO> handleValidationException(...) {
    // Returns structured validation errors
}
```

**Example Error Response:**

```json
{
  "status": 400,
  "message": "Validation failed",
  "timestamp": "2024/02/14 15:30:45",
  "errors": {
    "centerCode": "Center Code already exists",
    "contactPhone": "Phone number must be 10 digits"
  }
}
```

## 📜 Development Guidelines

### **Best Practices**

✅ **Controllers** should be thin – only handle request validation & response formatting.\
✅ **Service Layer** should contain business logic and be transactional.\
✅ **Repository Layer** should use JPA and avoid complex queries in code.\
✅ **Validation** should be handled via DTOs and custom annotations.

## 📩 Contact

For any queries, reach out at: [**namankhurana2017@gmail.com**](mailto\:namankhurana2017@gmail.com)

