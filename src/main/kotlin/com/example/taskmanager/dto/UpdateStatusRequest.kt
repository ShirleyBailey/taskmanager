package com.example.taskmanager.dto

import com.example.taskmanager.model.TaskStatus

data class UpdateStatusRequest(
    val status: TaskStatus
)