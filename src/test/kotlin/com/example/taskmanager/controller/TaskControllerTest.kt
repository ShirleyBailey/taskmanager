package com.example.taskmanager.controller

import com.example.taskmanager.exception.TaskNotFoundException
import com.example.taskmanager.service.TaskService
import org.junit.jupiter.api.Test
import org.mockito.kotlin.whenever
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono

@WebFluxTest(TaskController::class)
class TaskControllerTest {

    @Autowired
    lateinit var client: WebTestClient

    @MockBean
    lateinit var service: TaskService

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