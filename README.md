# Library Management System

This is a Spring Boot backend for a simple library management system. It handles user authentication and basic book operations like creating, updating, reserving, and deleting books.

The project uses JWT for authentication and has role-based access (ADMIN and USER).

---

## What this project does

* Users can register and log in
* Admins can add and manage books
* Users can view books and reserve them
* Reserved books can also be unreserved
* Basic CRUD operations for books

---

## Tech Stack

* Java
* Spring Boot
* Spring Security
* JWT
* Maven

---

## Base URL

```
http://localhost:8080/api/v1
```

---

## Authentication

### Register user

```
POST /auth/register
```

Example request:

```json id="auth1"
{
  "username": "john",
  "email": "john@gmail.com",
  "password": "12345",
  "role": "USER"
}
```

---

### Login

```
POST /auth/login
```

Example request:

```json id="auth2"
{
  "email": "john@gmail.com",
  "password": "12345"
}
```

Response:

```json id="auth3"
{
  "token": "jwt-token"
}
```

---

## Books

### Create a book (Admin)

```
POST /books/create
```

```json id="book1"
{
  "title": "Clean Code",
  "author": "Robert C. Martin"
}
```

---

### Get all books

```
GET /books
```

---

### Get a single book

```
GET /books/{id}
```

---

### Update book

```
PUT /books/update/{id}
```

---

### Delete book

```
DELETE /books/delete/{id}
```

---

### Reserve a book

```
POST /books/reserve/{id}
```

---

### Unreserve a book

```
POST /books/unreserve/{id}
```

---

## Notes

* This is a learning project
* Authentication is required for most endpoints
* Role-based access is enforced for admin actions

---

## Swagger

If the app is running, API docs are available at:

```
http://localhost:8080/swagger-ui/index.html
```

---

## Author

David Essel
