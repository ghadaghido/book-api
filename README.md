# Book API – Play Framework

A simple RESTful Book API built using the [Play Framework](https://www.playframework.com/) with Java and PostgreSQL.

---

## Features

- Create, Read, Update, Delete books
- JSON-based REST endpoints
- PostgreSQL integration via JPA/Hibernate
- Runs on Play Framework 2.9+ (Scala 2.13)
- Clean separation of Controller, Repository, and Model layers

---

## Tech Stack

- Java 17  
- Play Framework 2.9  
- PostgreSQL  
- JPA / Hibernate  
- SBT (Scala Build Tool)  
- JSON  

---

## API Endpoints

| Method | Endpoint           | Description              |
|--------|--------------------|--------------------------|
| GET    | `/books`           | Get all books            |
| GET    | `/books/:id`       | Get a book by ID         |
| POST   | `/books`           | Add a new book           |
| PUT    | `/books/:id`       | Update book by ID        |
| DELETE | `/books/:id`       | Delete book by ID        |

---

## Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/ghadaghido/book-api.git
cd book-api
