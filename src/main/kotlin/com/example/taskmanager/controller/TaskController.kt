package com.example.taskmanager.controller

import com.example.taskmanager.dto.*
import com.example.taskmanager.service.TaskService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import com.example.taskmanager.dto.UpdateStatusRequest

@RestController
@RequestMapping("/api/tasks")
class TaskController(private val service: TaskService) {

    @PostMapping
    fun create(@Valid @RequestBody request: CreateTaskRequest): Mono<TaskResponse> =
        service.create(request)

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): Mono<TaskResponse> =
        service.getById(id)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): Mono<Void> =
        service.delete(id)

    @PatchMapping("/{id}/status")
    fun updateStatus(
        @PathVariable id: Long,
        @RequestBody request: UpdateStatusRequest
    ): Mono<TaskResponse> =
        service.updateStatus(id, request.status)
}