package com.example.taskmanager.controller

import com.example.taskmanager.dto.CreateTaskRequest
import com.example.taskmanager.exception.TaskNotFoundException
import com.example.taskmanager.service.TaskService
import org.junit.jupiter.api.Test
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono

@WebFluxTest(TaskController::class)
class TaskControllerTest {

    @Autowired
    lateinit var client: WebTestClient

    @MockBean
    lateinit var service: TaskService

    @Test
    fun `create task should return 200`() {
        val request = CreateTaskRequest("title", "desc")

        whenever(service.create(request))
            .thenReturn(Mono.just(
                com.example.taskmanager.dto.TaskResponse(
                    1, "title", "desc", com.example.taskmanager.model.TaskStatus.NEW,
                    java.time.LocalDateTime.now(),
                    java.time.LocalDateTime.now()
                )
            ))

        client.post()
            .uri("/api/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .exchange()
            .expectStatus().isOk
    }

    @Test
    fun `should return 404 when task not found`() {
        whenever(service.getById(1))
            .thenReturn(Mono.error(TaskNotFoundException(1)))

        client.get()
            .uri("/api/tasks/1")
            .exchange()
            .expectStatus().isNotFound
    }
}