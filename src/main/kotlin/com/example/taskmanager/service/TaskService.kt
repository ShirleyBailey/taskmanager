package com.example.taskmanager.service

import com.example.taskmanager.dto.*
import com.example.taskmanager.mapper.toResponse
import com.example.taskmanager.model.*
import com.example.taskmanager.repository.TaskRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.time.LocalDateTime

@Service
class TaskService(private val repository: TaskRepository) {

    fun create(request: CreateTaskRequest): Mono<TaskResponse> {
        return Mono.fromCallable {
            val now = LocalDateTime.now()
            repository.save(
                Task(null, request.title, request.description, TaskStatus.NEW, now, now)
            )
        }.map { it.toResponse() }
    }

    fun getById(id: Long): Mono<TaskResponse> {
        return Mono.fromCallable {
            repository.findById(id) ?: throw RuntimeException("Not found")
        }.map { it.toResponse() }
    }

    fun delete(id: Long): Mono<Void> =
        Mono.fromRunnable { repository.deleteById(id) }

    fun updateStatus(id: Long, status: TaskStatus): Mono<TaskResponse> {
        return Mono.fromCallable {
            repository.updateStatus(id, status)
                ?: throw RuntimeException("Not found")
        }.map { it.toResponse() }
    }
}