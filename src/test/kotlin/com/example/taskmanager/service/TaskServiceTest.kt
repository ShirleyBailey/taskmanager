package com.example.taskmanager.service

import com.example.taskmanager.dto.CreateTaskRequest
import com.example.taskmanager.model.Task
import com.example.taskmanager.model.TaskStatus
import com.example.taskmanager.repository.TaskRepository
import com.example.taskmanager.exception.TaskNotFoundException
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.time.LocalDateTime

class TaskServiceTest {

    private val repository = mock<TaskRepository>()
    private val service = TaskService(repository)

    @Test
    fun `create task success`() {
        val now = LocalDateTime.now()
        val savedTask = Task(1, "title", "desc", TaskStatus.NEW, now, now)

        whenever(repository.save(any())).thenReturn(savedTask)

        val request = CreateTaskRequest("title", "desc")

        StepVerifier.create(service.create(request))
            .expectNextMatches {
                it.title == "title" && it.status == TaskStatus.NEW
            }
            .verifyComplete()
    }

    @Test
    fun `get task by id success`() {
        val now = LocalDateTime.now()
        val task = Task(1, "title", "desc", TaskStatus.NEW, now, now)

        whenever(repository.findById(1)).thenReturn(task)

        StepVerifier.create(service.getById(1))
            .expectNextMatches { it.id == 1L }
            .verifyComplete()
    }

    @Test
    fun `get task not found`() {
        whenever(repository.findById(1)).thenReturn(null)

        StepVerifier.create(service.getById(1))
            .expectError(TaskNotFoundException::class.java)
            .verify()
    }

    @Test
    fun `update status success`() {
        val now = LocalDateTime.now()
        val task = Task(1, "title", "desc", TaskStatus.DONE, now, now)

        whenever(repository.updateStatus(1, TaskStatus.DONE)).thenReturn(task)

        StepVerifier.create(service.updateStatus(1, TaskStatus.DONE))
            .expectNextMatches { it.status == TaskStatus.DONE }
            .verifyComplete()
    }

    @Test
    fun `delete task success`() {
        val now = LocalDateTime.now()
        val task = Task(1, "title", "desc", TaskStatus.NEW, now, now)

        whenever(repository.findById(1)).thenReturn(task)

        StepVerifier.create(service.delete(1))
            .verifyComplete()

        verify(repository).deleteById(1)
    }
}