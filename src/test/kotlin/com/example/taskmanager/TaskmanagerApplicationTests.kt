package com.example.taskmanager.service

import com.example.taskmanager.dto.CreateTaskRequest
import com.example.taskmanager.exception.TaskNotFoundException
import com.example.taskmanager.repository.TaskRepository
import com.example.taskmanager.model.Task
import com.example.taskmanager.model.TaskStatus
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.*
import reactor.test.StepVerifier
import java.time.LocalDateTime
import org.mockito.kotlin.whenever

class TaskmanagerApplicationTests {

	private val repository = mock<TaskRepository>()
	private val service = TaskService(repository)

	@Test
	fun `create task success`() {
		val request = CreateTaskRequest("title", "desc")

		val task = Task(1, "title", "desc", TaskStatus.NEW, LocalDateTime.now(), LocalDateTime.now())

		whenever(repository.save(any())).thenReturn(task)

		StepVerifier.create(service.create(request))
			.expectNextMatches { it.title == "title" }
			.verifyComplete()
	}

	@Test
	fun `get task not found`() {
		whenever(repository.findById(1)).thenReturn(null)

		StepVerifier.create(service.getById(1))
			.expectError(TaskNotFoundException::class.java)
			.verify()
	}
}