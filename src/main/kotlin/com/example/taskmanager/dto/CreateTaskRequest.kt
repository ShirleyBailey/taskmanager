package com.example.taskmanager.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CreateTaskRequest(
    @field:NotBlank
    @field:Size(min = 3, max = 100)
    val title: String,
    val description: String?
)