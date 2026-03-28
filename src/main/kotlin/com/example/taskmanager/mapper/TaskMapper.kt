package com.example.taskmanager.mapper

import com.example.taskmanager.dto.TaskResponse
import com.example.taskmanager.model.Task

fun Task.toResponse() = TaskResponse(
    id = id!!,
    title = title,
    description = description,
    status = status,
    createdAt = createdAt,
    updatedAt = updatedAt
)