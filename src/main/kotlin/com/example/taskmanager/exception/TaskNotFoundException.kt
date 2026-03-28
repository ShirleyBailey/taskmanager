package com.example.taskmanager.exception
class TaskNotFoundException(id: Long) : RuntimeException("Task $id not found")