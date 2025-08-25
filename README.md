# 📚 Library Management

A web application for managing a library with **books, authors, reservations, and users**.  
Built with **Spring Boot, PostgreSQL, Maven**, and a simple **HTML/CSS/Bootstrap frontend**.

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/4f9dec0f-5012-4058-ada5-025c0dbd612a" />


---

## ✨ Features
- 🔍 Search books and authors  
- 👤 User registration & profile management  
- 📖 Book reservations  
- 🛠 CRUD operations for books, authors, reservations, users  
- 🔑 Session-based authentication & authorization  
- 🧑‍💻 Admin panel with extended management  
- 📌 Extensible for new features  

---

## 🚀 Tech Stack
- **Java 21 (OpenJDK 21)**  
- **Spring Boot**  
- **PostgreSQL**  
- **Maven**  
- **Docker** (optional)  
- **Frontend**: HTML + CSS + Bootstrap  

---

## ⚙️ Installation

### Option 1: Run with Docker
```bash
mvn clean package
docker-compose up --build
```
App will be available at 👉 http://localhost:9000

Default login:

Admin → admin / admin

Users → Register via the web app

### Option 2: Run locally

Install JDK 21, Maven, PostgreSQL.

Create a PostgreSQL database:
```
CREATE DATABASE library;
```

Edit src/main/resources/application.properties:

spring.datasource.username=your_db_user
spring.datasource.password=your_db_pass


Run the project.


Access the app at: http://localhost:9000

---

## 📂 Project Structure

```
LibraryManagement/
├── src/
│   ├── main/
│   │   ├── java/com/library_management/librarymanagement/
│   │   │   ├── Controllers/            # REST & View controllers
│   │   │   ├── DTOs/                   # Data Transfer Objects
│   │   │   ├── Entities/               # Entity classes (Author, Book, User, Borrow)
│   │   │   ├── Repositories/           # Spring Data JPA repositories
│   │   │   ├── Security/               # Security config & user details
│   │   │   ├── Service/                # Business logic services
│   │   │   └── LibraryManagementApplication.java   # Main entry point
│   │   └── resources/
│   │       ├── db.migration/           # Database migration scripts (Flyway)
│   │       ├── templates/              # HTML templates (Bootstrap)
│   │       └── application.properties  # App configuration
│   └── test/                           # Unit tests

```

---

### 🧪 Testing

Tested with Postman (REST API) and via UI.

Example scenarios:

Admin: create, edit, delete books/authors/users/reservations.

User: register, login, search books/authors, make reservations.

Verified access control (users can’t access admin features).

### 📖 Usage

Register → log in → search for books/authors → reserve a book → manage reservations.

Admin can manage all entities via the admin panel.
