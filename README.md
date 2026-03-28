# Task Manager API

## 📌 Overview

This project is a RESTful task management service built with Kotlin and Spring Boot.

It supports creating, retrieving, updating, deleting tasks, and listing tasks with pagination and filtering.

---

## 🛠 Tech Stack

* Kotlin
* Spring Boot (WebFlux)
* Reactor (Mono, Flux)
* JdbcClient
* H2 Database
* Gradle

---

## 🚀 Features

### ✅ Task Management

* Create a task
* Get task by ID
* Delete a task
* Update task status

### ✅ Advanced

* Pagination (`page`, `size`)
* Filtering by status
* Sorting by `createdAt DESC`
* Validation (title length, not blank)
* Global exception handling

---

## 📂 Project Structure

```
src/main/kotlin
 ├── controller
 ├── service
 ├── repository
 ├── model
 ├── dto
 ├── exception
 └── config
```

---

## ▶️ Run Application

```bash
./gradlew bootRun
```

Server runs on:

```
http://localhost:8082
```

---

## 📡 API Endpoints

### 🔹 Create Task

POST /api/tasks

### 🔹 Get Task by ID

GET /api/tasks/{id}

### 🔹 Get Tasks (Pagination + Filter)

GET /api/tasks?page=0&size=10&status=NEW

### 🔹 Update Task Status

PATCH /api/tasks/{id}/status

### 🔹 Delete Task

DELETE /api/tasks/{id}

---

## 🧪 Testing

Run tests:

```bash
./gradlew test
```

---

## 💡 Notes

* Uses JdbcClient with native SQL (no ORM)
* Reactive service layer with Mono/Flux
* Blocking DB calls handled via boundedElastic scheduler

---

## 👨‍💻 Author

* Shirley Bailey
