# TaskFlow — Task Manager Application

A full-stack task management web application built with **Spring Boot** and **vanilla JavaScript**, featuring JWT-based authentication, role-based access control, and a modern dark UI.

---

## Features

- **JWT Authentication** — Secure login and registration with token-based sessions
- **Role-Based Access Control (RBAC)** — USER and ADMIN roles with different permissions
- **Task Management** — Create, edit, and delete tasks with title, status, and deadline
- **Kanban Board UI** — Tasks organized into To Do, In Progress, and Done columns
- **AI Assistant** — Integrated Claude AI to suggest and prioritize tasks
- **Deadline Tracking** — Visual indicators for overdue, due soon, and on-track tasks
- **Password Encryption** — BCrypt hashing for secure password storage

---

## Tech Stack

**Backend**
- Java 17
- Spring Boot 3.2.5
- Spring Security
- Spring Data JPA (Hibernate)
- MySQL
- JWT (jjwt 0.11.5)

**Frontend**
- HTML5, CSS3, Vanilla JavaScript
- Served as static files via Spring Boot

---

## Role Permissions

| Action | USER | ADMIN |
|--------|------|-------|
| Register / Login | ✅ | ✅ |
| Create task | ✅ | ✅ |
| View own tasks | ✅ | ✅ |
| View all tasks | ❌ | ✅ |
| Edit own task | ✅ | ✅ |
| Delete task | ❌ | ✅ |

---

## API Endpoints

| Method | Endpoint | Access | Description |
|--------|----------|--------|-------------|
| POST | `/register` | Public | Register a new user |
| POST | `/login` | Public | Login and receive JWT token |
| GET | `/tasks` | Authenticated | Get tasks (own for USER, all for ADMIN) |
| POST | `/tasks` | Authenticated | Create a new task |
| PUT | `/tasks/{id}` | Authenticated | Update an existing task |
| DELETE | `/tasks/{id}` | ADMIN only | Delete a task |

---

## Getting Started

### Prerequisites
- Java 17+
- Maven
- MySQL 8+

### Setup

**1. Clone the repository**
```bash
git clone https://github.com/yourusername/taskflow.git
cd taskflow
```

**2. Create the database**
```sql
CREATE DATABASE taskmanager_db;
```

**3. Configure application.properties**

Create `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/taskmanager_db
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

**4. Run the application**
```bash
mvn spring-boot:run
```

**5. Open in browser**
```
http://localhost:8080
```

---

## Project Structure

```
src/
└── main/
    ├── java/taskmanager/
    │   ├── TaskmanagerApplication.java
    │   ├── Task.java
    │   ├── User.java
    │   ├── controller/
    │   │   ├── AuthController.java
    │   │   └── TaskController.java
    │   ├── service/
    │   │   ├── TaskService.java
    │   │   └── UserService.java
    │   ├── repository/
    │   │   ├── TaskRepository.java
    │   │   └── UserRepository.java
    │   └── security/
    │       ├── SecurityConfig.java
    │       ├── JwtFilter.java
    │       └── JwtUtil.java
    └── resources/
        ├── application.properties
        └── static/
            ├── index.html
            ├── login.html
            ├── register.html
            └── dashboard.html
```

---

## Screenshots

> Add screenshots of your login page, dashboard, and task board here

---

## What I Learned

- Implementing stateless authentication with JWT in Spring Security
- Designing role-based access control for REST APIs
- Connecting Spring Boot to MySQL using JPA/Hibernate
- Building a responsive Kanban board UI without any frontend framework
- Integrating an external AI API (Anthropic Claude) into a web app

---

## Author

**Alister** — Computer Science Student  
[GitHub](https://github.com/yourusername) • [LinkedIn](https://linkedin.com/in/AlisterPaul)

---

## License

This project is open source and available under the [MIT License](LICENSE).
