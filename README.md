# Task Manager API

## 📌 Overview

A simple RESTful task management service built with Kotlin and Spring Boot.
It supports creating, retrieving, updating, and deleting tasks with reactive service handling.

---

## 🛠 Tech Stack

* Kotlin
* Spring Boot 3
* WebFlux (Project Reactor)
* JdbcClient (No ORM / JPA)
* H2 Database
* Gradle

---

## 🚀 Features

* Create Task
* Get Task by ID
* Get Tasks (Pagination + Filtering)
* Update Task Status
* Delete Task

---

## 📂 Architecture

controller → service → repository

---

## ▶️ Run Application

```bash
./gradlew bootRun
```

---

## 🧪 Run Tests

```bash
./gradlew test
```

---

## 📡 API Endpoints

### Create Task

POST /api/tasks

Request:

```json
{
  "title": "Prepare report",
  "description": "Monthly financial report"
}
```

---

### Get Task by ID

GET /api/tasks/{id}

---

### Get Tasks (Pagination + Filtering)

GET /api/tasks?page=0&size=10&status=NEW

---

### Update Task Status

PATCH /api/tasks/{id}/status

Request:

```json
{
  "status": "DONE"
}
```

---

### Delete Task

DELETE /api/tasks/{id}

---

## ⚠️ Notes

* Uses native SQL with JdbcClient (no ORM)
* Reactive service layer using Mono / Flux
* Blocking DB calls are wrapped properly
* Global exception handling implemented
* Input validation applied (title length, not blank)

---

## 💡 Author

* GitHub: https://github.com/ShirleyBailey/taskmanager
