package com.example.taskmanager.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException::class)
    fun handleNotFound(ex: TaskNotFoundException): ResponseEntity<Map<String, String?>> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(mapOf("error" to ex.message))
    }
}